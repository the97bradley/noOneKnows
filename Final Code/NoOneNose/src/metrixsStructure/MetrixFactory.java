package metrixsStructure;



public class MetrixFactory {

	public static MetrixsStruct getMetric(MetrixsID id) {
		switch (id) {
		case ATFD:
			return new ATFD();
		case CYCLO:
			return new CYCLO();
		case FDP:
			return new FDP();
		case LAA:
			return new LAA();
		case LOC:
			return new LOC();
		case NOAM:
			return new NOAM();
		case NOF:
			return new NOF();
		case NOM:
			return new NOM();
		case NOP:
			return new NOP();
		case NOPA:
			return new NOPA();
		case TCC:
			return new TCC();
		case WMC:
			return new WMC();
		case WOC:
			return new WOC();
		case SW:
			return new SW();
		default :
			return null;
		}
	}

}
