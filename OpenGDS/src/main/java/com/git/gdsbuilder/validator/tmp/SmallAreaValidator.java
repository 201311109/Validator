package com.git.gdsbuilder.validator.tmp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.DefaultFeatureCollection;
import org.opengis.feature.simple.SimpleFeature;

import com.git.gdsbuilder.type.feature.ErrorFeature;
import com.git.gdsbuilder.type.layer.ErrorLayer;
import com.git.gdsbuilder.type.result.DetailsValidatorResult;
import com.git.gdsbuilder.validator.factory.GeometryValidator;
import com.git.gdsbuilder.validator.factory.GeometryValidatorImpl;

public class SmallAreaValidator implements Callable<ErrorLayer> {

	private SimpleFeatureCollection validatorSfc;
	private double inputArea;
//	private HttpServletRequest request;

	public SmallAreaValidator(SimpleFeatureCollection validatorSfc, double inputArea) {
		this.validatorSfc = validatorSfc;
		this.inputArea = inputArea;
	//	this.request = request;
	}

	@Override
	public ErrorLayer call() throws Exception {

		ErrorLayer errLayer = new ErrorLayer();
		DefaultFeatureCollection errSFC = new DefaultFeatureCollection();
		List<DetailsValidatorResult> dtReports = new ArrayList<DetailsValidatorResult>();

		GeometryValidator geometryValidator = new GeometryValidatorImpl();
		SimpleFeatureIterator simpleFeatureIterator = validatorSfc.features();
		while (simpleFeatureIterator.hasNext()) {
			SimpleFeature simpleFeature = simpleFeatureIterator.next();
			ErrorFeature errFeature = geometryValidator.smallArea(simpleFeature, inputArea);
			if (errFeature != null) {
				errSFC.add(errFeature.getErrFeature());
				dtReports.add(errFeature.getDtReport());
			} else {
				continue;
			}
		}
		if (errSFC.size() > 0 && dtReports.size() > 0) {
			errLayer.setErrFeatureCollection(errSFC);
			errLayer.setDetailsValidatorReport(dtReports);
			return errLayer;
		} else {
			return null;
		}
	}
}
