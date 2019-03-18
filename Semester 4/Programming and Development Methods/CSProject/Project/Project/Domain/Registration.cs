using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project.Domain
{
    class Registration : IdEntity<KeyValuePair<int,KeyValuePair<RaceLength,RaceStyle>>>
    {
        public Registration(int athleteId, KeyValuePair<RaceLength, RaceStyle> raceId)
        {
            this.athleteId = athleteId;
            this.raceId = raceId;
        }

        public int athleteId { get; set; }
        public KeyValuePair<RaceLength,RaceStyle> raceId { get; set; }

        public KeyValuePair<int, KeyValuePair<RaceLength, RaceStyle>> GetId()
        {
            return new KeyValuePair<int, KeyValuePair<RaceLength, RaceStyle>> ( this.athleteId, this.raceId );
        }

        public void SetId(KeyValuePair<int, KeyValuePair<RaceLength, RaceStyle>> id)
        {
            this.athleteId = id.Key;
            this.raceId = id.Value;
        }
    }
}
