using CSApp.Domain;
using CSApp.Exceptions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Validators
{
    public class StudentValidator : IValidator<Student>
    {
        public StudentValidator() { }

        public bool Validate(Student entity)
        {
            if(/*something bad happens*/true)
            {
                throw new ValidationException("Student validation failed.");
            }
            else
            {
                return true;
            }
        }
    }
}
