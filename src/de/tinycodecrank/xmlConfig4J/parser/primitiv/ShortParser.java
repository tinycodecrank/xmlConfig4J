package de.tinycodecrank.xmlConfig4J.parser.primitiv;

import java.lang.reflect.Field;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.tinycodecrank.xmlConfig4J.LoadHelper;
import de.tinycodecrank.xmlConfig4J.SaveHelper;
import de.tinycodecrank.xmlConfig4J.parser.Parser;

import static de.tinycodecrank.xmlConfig4J.utils.Utils.*;

public class ShortParser implements Parser
{

	@Override
	public Class<?> parsedType()
	{
		return short.class;
	}

	@Override
	public void save(Element element, Object container, Document document, SaveHelper saveHelper)
	{
		element.setAttribute(VALUE, container.toString());
	}

	@Override
	public void load(Field field, Object parent, Node node, LoadHelper loadHelper)
		throws IllegalArgumentException,
		IllegalAccessException
	{
		Attr val = getAttribute(node, VALUE);
		field.setShort(parent, Short.parseShort(val.getValue()));
	}

	@Override
	public Object load(Node node, LoadHelper loadHelper)
	{
		Attr val = getAttribute(node, VALUE);
		return Short.parseShort(val.getValue());
	}
}