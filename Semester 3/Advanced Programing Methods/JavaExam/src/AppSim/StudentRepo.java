package AppSim;

import Domain.IdEntity;
import Repository.XMLRepository;
import Validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StudentRepo extends XMLRepository<String, Student> {


    public StudentRepo(Validator validator, String fileName) {
        super(validator, fileName,"student");
    }

    @Override
    protected Student getEntityFromElement(Element item, Document document) {
        String id = item.getAttribute("id");
        NodeList nodeList;

        nodeList = item.getElementsByTagName("name");
        String name = nodeList.item(0).getChildNodes().item(0).getNodeValue();

        nodeList = item.getElementsByTagName("group");
        int group = Integer.parseInt(nodeList.item(0).getChildNodes().item(0).getNodeValue());

        nodeList = item.getElementsByTagName("mail");
        String mail = nodeList.item(0).getChildNodes().item(0).getNodeValue();

        nodeList = item.getElementsByTagName("teacher");
        String t = nodeList.item(0).getChildNodes().item(0).getNodeValue();

        Student s = new Student(id,name,mail,t,group);
        return s;
    }

    @Override
    protected Node getEntityElement(Student entity, Document document) {
        Element root = document.createElement("student");

        root.setAttribute("id",entity.getId());
        root.appendChild(getLabelNode("name",entity.getName(), document));
        root.appendChild(getLabelNode("mail",entity.getMail(), document));
        root.appendChild(getLabelNode("teacher",entity.getTeacher(), document));
        root.appendChild(getLabelNode("group",""+entity.getGroup(), document));

        return root;

    }
}
