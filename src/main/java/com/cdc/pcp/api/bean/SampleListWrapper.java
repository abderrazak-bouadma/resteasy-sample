package com.cdc.pcp.api.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SampleListWrapper {

	private List<SampleVO> samples = new ArrayList<SampleVO>();

	public void add(SampleVO vo) {
		samples.add(vo);
	}

	public void remove(SampleVO vo) {
		if (!samples.isEmpty() && samples.contains(vo))
			samples.remove(vo);
	}

	public boolean removeById(String id) {
		if (id == null || id.isEmpty() || samples.isEmpty())
			return false;
		SampleVO sampleToRemove = null;
		for (SampleVO sample : samples) {
			if (sample.getId().equals(id)) {
				sampleToRemove = sample;
				break;
			}
		}
		if (sampleToRemove != null) {
			samples.remove(sampleToRemove);
			return true;
		} else {
			return false;
		}

	}

	public boolean contains(SampleVO vo) {
		return samples.contains(vo);
	}

	public void clear() {
		samples.clear();
	}

	public List<SampleVO> getSamples() {
		return samples;
	}

}
