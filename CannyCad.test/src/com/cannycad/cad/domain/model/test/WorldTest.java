package com.cannycad.cad.domain.model.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cannycad.cad.model.domain.DomainFactory;
import com.cannycad.cad.model.domain.DomainPackage;
import com.cannycad.cad.model.domain.Drawing;
import com.cannycad.cad.model.domain.DrawingElement;
import com.cannycad.cad.model.domain.World;

public class WorldTest
{
    DomainPackage domainPackage = null;
    DomainFactory factory = null;
    
    @Before
    public void setUp() throws Exception
    {
        domainPackage = DomainPackage.eINSTANCE;
        factory = domainPackage.getDomainFactory();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testParentElement()
    {
        Drawing drawing = factory.createDrawing();
        drawing.init();
        
        assertEquals("Bad ID", 1, drawing.getId());
        DrawingElement pe0 = drawing.getParentElement();
        assertEquals("Bad parent", null, pe0);

        World world1 = factory.createWorld();
        world1.init(drawing);

        assertEquals("Bad ID", 2, world1.getId());
        DrawingElement pe1 = world1.getParentElement();
        assertEquals("Bad parent", drawing, pe1);
        Drawing d1 = world1.getDrawing();
        assertEquals("Bad Drawing", drawing, d1);

        World world2 = factory.createWorld();
        world2.init(world1);
        
        assertEquals("Bad ID", 2, world1.getId());
        DrawingElement pe2 = world2.getParentElement();
        assertEquals("Bad parent", pe2, world1);
        Drawing d2 = world2.getDrawing();
        assertEquals("Bad Drawing", drawing, d2);
    }

}
