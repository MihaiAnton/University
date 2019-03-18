using CSApp.Domain;
using CSApp.Exceptions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Validators
{
    public class GradeValidator : IValidator<Grade>
    {
        public bool Validate(Grade entity)
        {
            if(/*not ok*/false)
            {
                throw new ValidationException("Grade validation error.");
            }
            else
            {
                return true;
            }
        }
    }
}
