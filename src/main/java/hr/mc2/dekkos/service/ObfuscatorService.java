package hr.mc2.dekkos.service;

import hr.mc2.dekkos.external.jchambers.id_obfuscator.*;
import hr.mc2.dekkos.external.jchambers.id_obfuscator.util.AlphabetBuilder;
import org.springframework.stereotype.Component;

@Component
public class ObfuscatorService {
    private final AlphabetCodec codec = new AlphabetCodec(new AlphabetBuilder()
        .includeUppercaseLatinLetters()
        .includeLowercaseLatinLetters()
        .build());
    private final BitRotationIntegerTransformer shuffler = new BitRotationIntegerTransformer(16);

    private final IntegerObfuscationPipeline pipeline = new IntegerObfuscationPipeline(codec, shuffler);

    public String obfuscate(Integer id) {
        return pipeline.obfuscate(id);
    }

    public Integer deobfuscate(String obfuscated) {
        return pipeline.deobfuscate(obfuscated);
    }
}
