package com.example.demo.service;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.InhousePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InhousePartServiceImpl implements InhousePartService {
    private InhousePartRepository repository;

    @Autowired
    public InhousePartServiceImpl(InhousePartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InhousePart> findAll() {
        return (List<InhousePart>) repository.findAll();
    }

    @Override
    public InhousePart findById(int theId) {
        Long theIdl = (long) theId;
        Optional<InhousePart> result = repository.findById(theIdl);

        InhousePart thePart = null;

        if (result.isPresent()) {
            thePart = result.get();
        } else {
            throw new RuntimeException("Did not find part id - " + theId);
        }

        return thePart;
    }

    @Override
    public void save(InhousePart thePart) {
        repository.save(thePart);
    }

    @Override
    public void deleteById(int theId) {
        Long theIdl = (long) theId;
        repository.deleteById(theIdl);
    }
}
