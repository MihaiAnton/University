using CSApp.Domain;
using CSApp.Exceptions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Validators
{
    public class HomeworkValidator : IValidator<Homework>
    {
        public bool Validate(Homework entity)
        {
            if(/*not ok*/false)
            {
                throw new ValidationException("Homework validation error.");
            }
            else
            {
                return true;
            }
        }
    }
}
