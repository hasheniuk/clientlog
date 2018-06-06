package org.clientlog.search.specification;

import org.clientlog.domain.Client;
import org.clientlog.dto.ClientRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClientSearchSpecification implements Specification<Client> {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean anyMatch;

    public ClientSearchSpecification(ClientRequest clientRequest) {
        this.firstName = clientRequest.getFirstName();
        this.middleName = clientRequest.getMiddleName();
        this.lastName = clientRequest.getLastName();
        this.email = clientRequest.getEmail();
        this.phone = clientRequest.getPhone();
        this.anyMatch = clientRequest.getAnyMatch();
    }

    @Override
    public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(firstName)) {
            predicates.add(likeIgnoreCase(root, cb, "firstName", firstName));
        }

        if (!StringUtils.isEmpty(middleName)) {
            predicates.add(likeIgnoreCase(root, cb, "middleName", middleName));
        }

        if (!StringUtils.isEmpty(lastName)) {
            predicates.add(likeIgnoreCase(root, cb, "lastName", lastName));
        }

        if (!StringUtils.isEmpty(email)) {
            predicates.add(likeIgnoreCase(root, cb, "email", email));
        }

        if (!StringUtils.isEmpty(phone)) {
            predicates.add(likeIgnoreCase(root, cb, "phone", phone));
        }

        if (predicates.isEmpty()) return null;

        Predicate[] predicateArray = predicates.toArray(new Predicate[0]);

        return anyMatch != null && anyMatch ? cb.or(predicateArray) : cb.and(predicateArray);
    }

    private Predicate likeIgnoreCase(Root<Client> root, CriteriaBuilder cb, String name, String value) {
        return cb.like(cb.lower(root.get(name)), "%" + value + "%");
    }
}
