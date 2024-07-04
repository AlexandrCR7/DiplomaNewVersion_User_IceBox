package ru.gb.userIceBoxCheck.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.repository.IceBoxRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
public class IseBoxService {

    private final IceBoxRepository iceBoxRepository;

    public List<IceBox> findAll(){
        return iceBoxRepository.findAll();
    }

    public Optional<IceBox> findById(Long id){
        return iceBoxRepository.findById(id);
    }

    public void deleteById(Long id){
        iceBoxRepository.deleteById(id);
    }

    public void saveIseBox(IceBox iceBox){
        iceBoxRepository.save(iceBox);
    }

    public IceBox addIceBox(List<String> newBox){
        IceBox iceBox = new IceBox();
        iceBox.setList(newBox);
        return iceBox;
    }
}
