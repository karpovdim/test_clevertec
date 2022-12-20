package by.karpov.test_clevertec.dto.converter;


public interface DtoConverter<S, D> {

    S convertToEntity(D dto);

    D convertToDto(S entity);

}
