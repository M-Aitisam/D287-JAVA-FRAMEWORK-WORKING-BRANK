package com.example.demo.service;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.OutsourcedPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutsourcedPartServiceImpl implements OutsourcedPartService {
    private OutsourcedPartRepository repository;

    @Autowired
    public OutsourcedPartServiceImpl(OutsourcedPartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OutsourcedPart> findAll() {
        return (List<OutsourcedPart>) repository.findAll();
    }

    @Override
    public OutsourcedPart findById(int theId) {
        Long theIdl = (long) theId;
        Optional<OutsourcedPart> result = repository.findById(theIdl);

        OutsourcedPart thePart = null;

        if (result.isPresent()) {
            thePart = result.get();
        } else {
            throw new RuntimeException("Did not find part id - " + theId);
        }

        return thePart;
    }

    @Override
    public void save(OutsourcedPart thePart) {
        repository.save(thePart);

    }

    @Override
    public void deleteById(int theId) {
        Long theIdl = (long) theId;
        repository.deleteById(theIdl);
    }
}
