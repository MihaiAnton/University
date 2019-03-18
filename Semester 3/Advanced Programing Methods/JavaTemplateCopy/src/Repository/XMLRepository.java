package Repository;

import Domain.IdEntity;
import Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class XMLRepository<ID, E extends IdEntity<ID>> extends StorageRepository{

    private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();



    public XMLRepository(Validator validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    public void readFromFile() {
        try{

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(getFileName()));

            Element element = document.getDocumentElement();

            NodeList nodeList = element.getElementsByTagName("grade");
            for(int i=0;i<nodeList.getLength();i++){

                Element item = (Element)nodeList.item(i);

                E entity = getEntityFromElement(item, document);

                save(entity);

            }


        }
        catch(Exception e){}
    }

    protected abstract E getEntityFromElement(Element item, Document document);


    @Override
    public void writeToFile() {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("gradeList");

            for (Object object : findAll()) {

                E entity = (E)object;
                root.appendChild(getEntityElement(entity, document));
            }

            document.appendChild(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource  = new DOMSource(document);
            StreamResult streamResult = new StreamResult(getFileName());

            transformer.transform(domSource,streamResult);

        }
        catch(Exception e){}

    }

    protected abstract Node getEntityElement(E entity, Document document);

    private static Node getLabelNode(String label, String value, Document document){

        Node labelNode = document.createElement(label);
        labelNode.appendChild(document.createTextNode(value));
        return labelNode;

    }
}


























