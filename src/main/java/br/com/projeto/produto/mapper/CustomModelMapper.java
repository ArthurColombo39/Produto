package br.com.projeto.produto.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomModelMapper {

    public static ModelMapper mapper = new ModelMapper();

    public static <Origin, Destination> Destination parseObject(Origin origin,
                                                                 Class<Destination> destination){
        return mapper.map(origin, destination);
    }

    public static <Origin, Destination> List<Destination>
    parseObjectList(List<Origin> origins,
                    Class<Destination> destination){
        List<Destination> destinationList = new ArrayList<>();
        for(Origin origin : origins){
            destinationList.add(mapper.map(origin, destination));
        }
        return destinationList;
    }

}
