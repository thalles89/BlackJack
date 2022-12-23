package abstractfactory;

public class VersionOneParserFactory implements ParserFactory{

    @Override
    public Parser createParser() {
        return new VersionOneParserImpl();
    }
}
