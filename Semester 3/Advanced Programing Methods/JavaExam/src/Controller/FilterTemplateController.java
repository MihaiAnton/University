package Controller;

import Utils.Event;
import Utils.ServiceEvent;

public abstract class FilterTemplateController<E> extends TemplateController {




    @Override
    public void updateFields(Object newValue) {}

    @Override
    public Object getEntityFromFields() {return null;}

    @Override
    public void populateList() {
        //service.getAll();

        this.controllerModel.clear();
        //this.service.get....forEach(
    }

    @Override
    public void notify(Event event) {
        this.populateList();
    }
}

