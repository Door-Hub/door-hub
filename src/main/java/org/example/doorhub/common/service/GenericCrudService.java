package org.example.doorhub.common.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.example.doorhub.common.rsql.SpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;

public abstract class GenericCrudService<ENTITY, ID, CREATE_DTO, UPDATE_DTO, PATCH_DTO, RESPONSE_DTO>
{
    public RESPONSE_DTO create(CREATE_DTO createDto)
    {
        ENTITY saved = save( createDto );
        return getMapper().toResponseDto( saved );
    }

    protected abstract GenericDtoMapper<ENTITY, CREATE_DTO, UPDATE_DTO, RESPONSE_DTO> getMapper();

    protected abstract GenericSpecificationRepository<ENTITY, ID> getRepository();

    protected abstract ENTITY save( CREATE_DTO createDto );

    protected abstract ENTITY updateEntity( UPDATE_DTO updateDto, ENTITY entity );

    public Page<RESPONSE_DTO> getAll( Pageable pageable, String predicate )
    {
        Specification<ENTITY> specification = SpecificationBuilder.build( predicate );
        if( specification == null )
        {
            return getRepository().findAll( pageable )
                                  .map( entity -> getMapper().toResponseDto( entity ) );
        }
        return getRepository().findAll( specification, pageable )
                              .map( entity -> getMapper().toResponseDto( entity ) );
    }

    public RESPONSE_DTO getById( ID id )
    {
        ENTITY entity = getRepository()
            .findById( id )
            .orElseThrow( () -> throwEntityNotFoundException( id, getEntityClass().getSimpleName() ) );
        return getMapper().toResponseDto( entity );
    }

    // todo make message generic
    public RuntimeException throwEntityNotFoundException( ID id, String entityName )
    {
        String message = "%s with id=%s not found".formatted( entityName, id.toString() );
        return new EntityNotFoundException( message );
    }

    protected abstract Class<ENTITY> getEntityClass();

    public RESPONSE_DTO update( ID id, UPDATE_DTO updateDto )
    {
        ENTITY entity = getRepository()
            .findById( id )
            .orElseThrow( () -> throwEntityNotFoundException( id, getEntityClass().getSimpleName() ) );
        ENTITY saved = updateEntity( updateDto, entity );
        return getMapper().toResponseDto( saved );
    }

    public RESPONSE_DTO patch( ID id, PATCH_DTO patchDto ) throws IllegalAccessException, NoSuchFieldException
    {
        ENTITY entity = getRepository().findById( id )
                                       .orElseThrow( EntityNotFoundException::new );

        Class<?> entityClass = entity.getClass();
        Class<?> patchDtoClass = patchDto.getClass();

        for( Field field : patchDtoClass.getDeclaredFields() )
        {
            field.setAccessible( true );
            Object value = field.get( patchDto );
            if( value != null )
            {
                Field entityClassField = entityClass.getDeclaredField( field.getName() );
                entityClassField.setAccessible( true );
                entityClassField.set( entity, value );
            }
        }

        ENTITY saved = getRepository().save( entity );

        return getMapper().toResponseDto( saved );
    }

    public void delete( ID id )
    {
        if( !getRepository().existsById( id ) )
        {
            throw throwEntityNotFoundException( id, getEntityClass().getSimpleName() );
        }
        getRepository().deleteById( id );
    }
}
