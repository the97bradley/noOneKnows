package codesmell;



public class CodeSmellFactory {

	public static CodeSmell getCodeSmell(CodeSmellId id) {
		switch (id) {
		case BRAIN_CLASS:
			return new BrainClass();
		case BRAIN_METHOD:
			return new BrainMethod();
		case COMPLEX_METHOD:
			return new ComplexMethod();
		case DATA_CLASS:
			return new DataClass();
		case FEATURE_ENVY:
			return new FeatureEnvy();
		case GOD_CLASS:
			return new GodClass();
		case LONG_METHOD:
			return new LongMethod();
		case LARGE_CLASS:
			return new LargeClass();
		case PRIMITIVE_OBSESSION:
			return new PrimitiveObsession();
		case LONG_PARAMETER_LIST:
			return new LongParameterList();
		default:
			return null;
		}
	}

}
