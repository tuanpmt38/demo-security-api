package vn.shippo.demosecurity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.shippo.demosecurity.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
