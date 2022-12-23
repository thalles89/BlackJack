package abstractfactory;

import org.w3c.dom.Document;

public class VersionTwoParserImpl implements Parser{

    public Document parse(String document){
        return (Document) new VersionTwoParserImpl();
    }
}
