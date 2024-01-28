package mx.bidgroup.tec.tni.nomibanco.services;

import mx.bidgroup.tec.tni.nomibanco.dtos.DependencyDto;

public interface IDependencyService {

    DependencyDto createDependency(DependencyDto dependencyDto);
    DependencyDto updateDependency(DependencyDto dependencyDto);
    void deleteDependency(Long id);
    <Optional> DependencyDto getFirstDependency();

}
