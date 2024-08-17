package com.unipi.mns.mnscrm00.utilities;

import com.unipi.mns.mnscrm00.constants.Constants;
import com.unipi.mns.mnscrm00.dto.abstracts.*;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import com.unipi.mns.mnscrm00.entities.data.*;
import com.unipi.mns.mnscrm00.exceptions.ListConversionException;

import java.util.ArrayList;
import java.util.List;

public class ListConverter {

    public static <E extends Sendable<D>, D> List<D> convertEntitiesToDTOList(List<E> entities, int conversionType) {
        List<D> dtoList = new ArrayList<>();

        for (E entity : entities) {
            D dto;
            switch (conversionType) {
                case Constants.DTO.CONVERT_TO_DTO_MINIMAL:
                    dto = entity.toDTOMinimal();
                    break;
                case Constants.DTO.CONVERT_TO_DTO_SIMPLE:
                    dto = entity.toDTOSimple();
                    break;
                case Constants.DTO.CONVERT_TO_DTO_COMPLETE:
                    dto = entity.toDTOComplete();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid conversion type");
            }
            dtoList.add(dto);
        }

        return dtoList;
    }
}
