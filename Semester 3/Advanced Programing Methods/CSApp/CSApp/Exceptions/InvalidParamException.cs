using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSApp.Exceptions
{
    class InvalidParamException : Exception
    {
        public InvalidParamException(string message) : base(message)
        {
        }
    }
}
