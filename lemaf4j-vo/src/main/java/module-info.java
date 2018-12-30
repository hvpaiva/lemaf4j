open module lemaf4j.vo {

    requires java.activation;
    requires javax.mail.api;
    requires java.instrument;
    requires java.persistence;
    requires java.sql;
    requires java.validation;
    requires java.xml.bind;
    requires javax.interceptor.api;
    requires javax.inject;
    requires java.transaction;

    requires com.sun.xml.txw2;
    requires com.sun.istack.runtime;
    requires org.jvnet.staxex;
    requires com.sun.xml.fastinfoset;
    requires utils4j;
    requires cdi.api;
    requires jsr305;

    requires jdk.unsupported;

}
