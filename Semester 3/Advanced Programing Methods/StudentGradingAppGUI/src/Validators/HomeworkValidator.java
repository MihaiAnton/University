package Validators;

import Domain.Homework;
import Exceptions.ValidationException;

/**
 *  Validator class specialized in Homework
 */
public class HomeworkValidator implements Validator<Homework> {


    /**
     *
     * @param h
     * @throws ValidationException
     */
    @Override
    public void validate(Homework h)/*throws ValidationException*/{
        String err = "";
        try{
            validateId(h.getId());
        }catch(Exception e){
            err = err + e.getMessage();
        }

        try{
            validateDates(h.getTargetWeek(), h.getDeadlineWeek());
        }catch(Exception e){
            err = err + e.getMessage();
        }

        try{
            validateAssignmentWeek(h.getAssignmentWeek());
        }catch(Exception e){
            err = err + e.getMessage();
        }

        if(err.length() > 0){
            throw new ValidationException(err);
        }

    }

    /**
     *
     * @param id
     * @throws ValidationException
     */
    private void validateId(int id) /*throws ValidationException*/{
        if(id <= 0){
            throw new ValidationException("Incorrect homework id.");
        }
    }

    /**
     *
     * @param targetWeek
     * @param deadlineWeek
     * @throws ValidationException
     */
    private void validateDates(int targetWeek, int deadlineWeek) /*throws ValidationException*/{
        String s = "";
        if(targetWeek < 1 || targetWeek > 14)
            s = s + "Incorrect target week value." + "\n";
        if(deadlineWeek < 1 || deadlineWeek > 14)
            s = s + "Incorrect deadline week value." + "\n";

        if(s.length() > 0){
            throw new ValidationException(s);
        }
    }

    /**
     *
     * @param week
     * @throws ValidationException
     */
    public void validateAssignmentWeek(int week) /*throws ValidationException*/{
        if(week < 1 || week > 14)
            throw new ValidationException("Incorrect assignment week.");
    }
}
