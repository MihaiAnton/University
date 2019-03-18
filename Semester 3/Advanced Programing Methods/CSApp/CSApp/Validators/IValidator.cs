using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Validators
{
    public interface IValidator<E>
    {
        /// <summary>
        ///     Validates.
        /// </summary>
        /// <param name="entity"></param>
        /// <returns>
        ///     Returns true if valid  
        /// </returns>
        /// <exception>
        ///     Throws Validation Exeption
        /// </exception>
        bool Validate(E entity);
    }
}
