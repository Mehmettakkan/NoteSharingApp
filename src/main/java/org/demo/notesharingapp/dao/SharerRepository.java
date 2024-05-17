package org.demo.notesharingapp.dao;

import org.demo.notesharingapp.entity.Sharer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharerRepository extends JpaRepository<Sharer, Integer> {

    List<Sharer> findSharerByFirstName(String firstName);

    List<Sharer> findSharerByLastName(String lastName);

    List<Sharer> findSharerByFirstNameAndLastName(String firstName, String lastName);

    Sharer findByEmail(String email);

    Sharer findSharerByUserName(String userName);

}