package core.listners;

import core.report.ExtendReport;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static core.configs.TestConfigs.API_SLA_TIME;

public class APIResponseFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        String responseStr = ResponsePrinter.print(response,
                response,
                new PrintStream(new WriterOutputStream(new StringWriter())),
                LogDetail.ALL,
                true);

        String requestStr = RequestPrinter.print(requestSpec,
                requestSpec.getMethod(),
                requestSpec.getURI(),
                LogDetail.ALL,
                new PrintStream(new WriterOutputStream(new StringWriter())),
                true);

        ExtendReport.logInfoMessage("<b>REQUEST</b> :<br><pre>" + requestStr + "</pre>");

        long responseTime = response.getTime();

        if (responseTime < API_SLA_TIME) {
            ExtendReport.logInfoMessage("Response time : <font color='green'><b>" + responseTime + "</b> </font>ms");
        } else {
            ExtendReport.logInfoMessage("Response time : <font color='red'><b>" + responseTime + "</b> </font>ms");
        }

        ExtendReport.logInfoMessage("<b>RESPONSE </b>:<br><pre>" + responseStr + "</pre>");

//        List<String> list = new ArrayList<>() ;
//        list.add("Test");
//        list.add("Test1");
//        list.add("Test3");
//        list.add("Test2");
//        Markup m=MarkupHelper.createUnorderedList(list);
//        ExtendReport.logInfoMessage(m);
        return response;
    }

}
