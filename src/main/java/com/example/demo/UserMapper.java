package com.example.demo;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;

@Component
public class UserMapper {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ContextRepository contextRepository;

    @TrackTransation
    @Transactional(propagation = REQUIRED, readOnly = true)
    public UserDto toDto(User user) {
        System.out.println("tooLongOperation - active ? " + isActualTransactionActive());

        UserDto dto = new UserDto();

        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setId(user.getId());

        reattach(user);

        // je récupère les sous-objets
        // ca va forcément pêter car je suis hors transaction !
        List<Context> contextes = user.getContexts();
        List<String> contextNames = contextes.stream().map(c -> c.getName()).collect(toList());
        dto.setContextNames(contextNames);

        return dto;
    }

    public void reattach(Object o) {
        // voir https://vladmihalcea.com/a-beginners-guide-to-jpa-hibernate-entity-state-transitions/
        // TODO a voir : ca ne doit fonctionner que pour les cas pour lequel on dispose d'un Proxy Hibernate ?
        Session session = (Session) entityManager.getDelegate();
        session.update(o);
    }

    @Transactional(propagation = REQUIRED, readOnly = true)
    public List<UserDto> toDtos(List<User> users) {
        return users.stream().map(this::toDto).collect(toList());
    }

    public User toEntity(UserDto userDto) {

        User u = new User();
        u.setId(userDto.getId());
        u.setFirstname(userDto.getFirstname());
        u.setLastname(userDto.getLastname());

        // là : pas le choix, il faut passer par le repository
        List<String> contextNames = userDto.getContextNames();
        if(contextNames != null) {
            List<Context> contexts = contextNames.stream().map(n -> contextRepository.findByName(n)).collect(toList());
            u.setContexts(contexts);
        }
        return u;
    }



}
