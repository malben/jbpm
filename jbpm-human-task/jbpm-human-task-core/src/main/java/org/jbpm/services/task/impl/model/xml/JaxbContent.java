package org.jbpm.services.task.impl.model.xml;

import static org.jbpm.services.task.impl.model.xml.AbstractJaxbTaskObject.unsupported;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.jbpm.services.task.utils.ContentMarshallerHelper;
import org.kie.api.task.model.Content;
import org.kie.internal.jaxb.StringKeyObjectValueMapXmlAdapter;

@XmlRootElement(name="content")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE, fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class JaxbContent implements Content {

    @XmlElement
    private Long id;

    @XmlElement
    @XmlSchemaType(name="base64Binary")
    private byte[] content = null;
    
    @XmlJavaTypeAdapter(StringKeyObjectValueMapXmlAdapter.class)
    private Map<String, Object> contentMap = null;
    
    public JaxbContent() { 
        // default
    }
    
    public JaxbContent(Content content) { 
        initialize(content);
    }
    
    @SuppressWarnings("unchecked")
    public void initialize(Content content) { 
        if( content == null || content.getId() == -1) { 
            return; 
        }
        this.id = content.getId();
        this.content = content.getContent();
        Object unmarshalledContent = ContentMarshallerHelper.unmarshall(content.getContent(), null);
        if( unmarshalledContent != null && unmarshalledContent instanceof Map ) { 
           contentMap = (Map<String, Object>) unmarshalledContent;
        }
    }
    
    @Override
    public byte[] getContent() {
        return content;
    }
   
    public byte[] getSerializedContent() { 
        return this.content;
    }

    public void setSerializedContent(byte [] content) { 
        this.content = content;
    }

    public Map<String, Object> getContentMap() { 
        return this.contentMap;
    }

    public void setContentMap(Map<String, Object> map) { 
        this.contentMap = map;
    }

    @Override
    public long getId() {
        return this.id;
    } 
    
    public void setId(Long id) {
        this.id = id; 
    }

    @Override
    public void writeExternal( ObjectOutput out ) throws IOException {
        unsupported(Content.class);
    }

    @Override
    public void readExternal( ObjectInput in ) throws IOException, ClassNotFoundException {
        unsupported(Content.class);
    } 
    
}
