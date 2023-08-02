package utils;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name, String language, String address)
    {
        AddPlace p =new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }

    public String   getGraphPayload(int characterId , int eposideId)
    {
        return "{\"query\":\"query($charaterId: Int!,$episodeId: Int!){\\n  character(characterId: $charaterId) \\n  {\\n    name\\n    gender\\n    type\\n    status\\n    id\\n  }\\n  location(locationId:16)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId:$episodeId)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters:{name:\\\"Jenin\\\"} )\\n  {\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      type\\n    }\\n  }\\n}\",\"variables\":{\"charaterId\":"+characterId+",\"episodeId\":"+eposideId+"}}";
    }

    public String mutationGraphPayload(String newCharName)
    {
        return "{\"query\":\"mutation($locationName: String!,$characterName: String!,$episodeName: String! )\\n{\\n  createLocation(location:{name:$locationName,type:\\\"northzone\\\",dimension:\\\"143\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{name:$characterName,type:\\\"comediean\\\",status:\\\"Alive\\\",species:\\\"Fantasy\\\",gender:\\\"male\\\",image:\\\"png\\\",originId: 1349,locationId:1349})\\n  {\\n    id\\n  }\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"1981 July\\\",episode:\\\"Exclusive\\\"})\\n  {\\n    id\\n  }\\n}\",\"variables\":{\"locationName\":\"Congo\",\"characterName\":\""+newCharName+"\",\"episodeName\":\"Khus Hua\"}}";
    }


}
