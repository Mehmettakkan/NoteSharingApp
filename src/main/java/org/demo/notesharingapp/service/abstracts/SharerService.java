package org.demo.notesharingapp.service.abstracts;

import org.demo.notesharingapp.entity.Sharer;

import java.util.List;

public interface SharerService {
    Sharer saveSharer(Sharer sharer);

    Sharer updateSharer(int sharerId, Sharer updatedSharer);

    void deleteSharer(int sharerId);

    List<Sharer> getSharers();

    Sharer getSharerById(int sharerId);

    Sharer getSharerByEmail(String email);

    Sharer getSharerByUsername(String username);

}