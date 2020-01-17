package co.rodnan.example;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import java.util.List;

public class ClassImportIntegrator implements Integrator {

    private final List<? extends Class> classImportList;

    public ClassImportIntegrator() {
        this.classImportList = List.of(PostDTO.class);
    }

    @Override
    public void integrate(
            Metadata metadata,
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
        for (Class classImport : classImportList) {
            metadata.getImports().put(
                    classImport.getSimpleName(),
                    classImport.getName()
            );
        }
    }

    @Override
    public void disintegrate(
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
    }
}
