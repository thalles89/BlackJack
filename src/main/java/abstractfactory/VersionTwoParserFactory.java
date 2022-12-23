package abstractfactory;

public class VersionTwoParserFactory implements ParserFactory{

    @Override
    public Parser createParser() {
        return new VersionTwoParserImpl();
    }
}