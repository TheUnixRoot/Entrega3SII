/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.toPDF.PdfCreator;
import grupoj.prentrega1.Evento;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * @deprecated @author juanp
 */
@Named(value = "printBean")
@RequestScoped
public class PrintBean implements Serializable {

    @Inject
    private PersistenceMock persistencia;
    private PdfCreator pdf;
    private Evento ev;

    public Evento getEv() {
        return ev;
    }

    public void setEv(Evento ev) {
        this.ev = ev;
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public PdfCreator getPdf() {
        return pdf;
    }

    public void setPdf(PdfCreator pdf) {
        this.pdf = pdf;
    }

    /**
     * Dado el evento pasado como parametro de la web, se obtiene un pdf con los
     * datos relevantes del mismo
     *
     * @return Archivo pdf generado
     */
    public StreamedContent getFile() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Evento evprima = new Evento();
        evprima.setId(Long.parseLong(params.get("id")));
        setEv(persistencia.getListaEventos().get(
                persistencia.getListaEventos().indexOf(evprima)));
        pdf = new PdfCreator(this.ev);

        StreamedContent stc = new DefaultStreamedContent(
                new ByteArrayInputStream(pdf.getStream()),
                "application/pdf", ev.getNombre() + ".pdf");
        return stc;
    }

}
