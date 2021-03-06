package de.tinycodecrank.xmlConfig4J.parser;

import java.util.UUID;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.tinycodecrank.xmlConfig4J.LoadHelper;
import de.tinycodecrank.xmlConfig4J.SaveHelper;

import static de.tinycodecrank.xmlConfig4J.utils.Utils.*;

public class UUIDParser implements Parser
{
	private static final String MOST_SIG = "mostSigBits";
	private static final String LEAST_SIG = "leastSigBit";
	
	@Override
	public Class<?> parsedType()
	{
		return UUID.class;
	}

	@Override
	public void save(Element element, Object container, Document document, SaveHelper saveHelper)
	{
		UUID uuid = (UUID) container;
		if(uuid == null)
		{
			element.setAttribute(NULL, TRUE);
		}
		else
		{
			element.setAttribute(MOST_SIG, Long.toString(uuid.getMostSignificantBits()));
			element.setAttribute(LEAST_SIG, Long.toString(uuid.getLeastSignificantBits()));
		}
	}

	@Override
	public Object load(Node node, LoadHelper loadHelper)
	{
		if(loadIsNotNull(node, loadHelper))
		{
			String mostSigBit = getAttribute(node, MOST_SIG).getValue();
			String leastSigBit = getAttribute(node, LEAST_SIG).getValue();
			return new UUID(Long.parseLong(mostSigBit), Long.parseLong(leastSigBit));
		}
		else
		{
			return null;
		}
	}
}