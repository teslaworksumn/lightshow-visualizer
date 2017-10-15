package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;

public class ArcBush extends Bush {

	private final int start, size, arcWidth;

	protected ArcBush(Element e) {
		super(e);

		start = Integer.parseInt(e.attributeValue("start"));
		size = Integer.parseInt(e.attributeValue("size"));
		arcWidth = Integer.parseInt(e.attributeValue("arcwidth"));
	}

	@Override
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		g2d.setPaint(makeTexturePaint(channelValues));
		g2d.translate(x, y);

		Arc2D outer = new Arc2D.Double();
		outer.setArcByCenter(width / 2, height / 2, width / 2, start, size, Arc2D.PIE);

		Arc2D inner = new Arc2D.Double();
		inner.setArcByCenter(width / 2, height / 2, (width / 2) - arcWidth, start, size, Arc2D.PIE);

		Area bush = new Area(outer);
		bush.subtract(new Area(inner));

		g2d.fill(bush);

		g2d.translate(0 - x, 0 - y);
	}
}
