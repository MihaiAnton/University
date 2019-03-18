using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Domain
{
    class RaceType : IdEntity<KeyValuePair<RaceLength, RaceStyle>>
    {
        public RaceType(RaceLength length, RaceStyle style)
        {
            this.length = length;
            this.style = style;
        }

        public RaceLength length { get; set; }
        public RaceStyle style { get; set; }



        public KeyValuePair<RaceLength, RaceStyle> GetId()
        {
            return new KeyValuePair<RaceLength, RaceStyle>(length, style);
        }

        public void SetId(KeyValuePair<RaceLength, RaceStyle> id)
        {
            this.length = id.Key;
            this.style = id.Value;
        }
    }
}
