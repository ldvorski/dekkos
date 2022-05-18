package hr.mc2.dekkos.service;

import hr.mc2.dekkos.external.jchambers.id_obfuscator.*;
import hr.mc2.dekkos.external.jchambers.id_obfuscator.util.AlphabetBuilder;

public class ObfuscatorImpl {

    static final AlphabetCodec codec = new AlphabetCodec(new AlphabetBuilder()
            .includeUppercaseLatinLetters()
            .includeLowercaseLatinLetters()
            .build());


    static final IntegerObfuscationPipeline pipeline =
            new IntegerObfuscationPipeline( codec);

    public static String obfuscate(Integer id){
        return pipeline.obfuscate(id);
    }
    public static Integer deobfuscation(String obfuscated){
        return pipeline.deobfuscate(obfuscated);
    }
}
