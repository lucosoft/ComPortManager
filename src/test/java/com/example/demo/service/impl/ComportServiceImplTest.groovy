package com.example.demo.service.impl

class ComportServiceImplTest extends spock.lang.Specification {

    def service = new ComportServiceImpl()

    def "GetComPort"() {
        when: "se llama al metodo"
        service.getComPort()

        then:
        noExceptionThrown()
    }

    def "OpenComPort"() {
        when: "se llama al metodo"
        service.openComPort()

        then:
        noExceptionThrown()
    }

    def "CloseComPort"() {
        when: "se llama al metodo"
        service.closeComPort()

        then:
        noExceptionThrown()
    }
}
