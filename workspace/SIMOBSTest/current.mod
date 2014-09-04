\model{
	\statevector{
		\type{short}{P0, P1}
	}

	\initial{
		P0 = [pipe.common.dataLayer.Marking@d1e7c2]; P1 = [pipe.common.dataLayer.Marking@3afb99]; 
	}
	\transition{T0}{
		\condition{P0 > 0}
		\action{
			next->P0 = P0 - [pipe.common.dataLayer.Marking@1f9f0f2];
			next->P1 = P1 + [pipe.common.dataLayer.Marking@39d325];
		}
		\weight{1.0}
	}
}

\solution{
	\method{sor}

}\performance{
	\statemeasure{Enabled probability for transition T0}{
		\estimator{mean}
		\expression{(P0 > 0) ? 1 : 0}
	}
	\countmeasure{Throughput for transition T0}{
		\estimator{mean}
		\precondition{1}
		\postcondition{1}
		\transition{T0}
	}
	\statemeasure{Mean tokens on place P0}{
		\estimator{mean variance distribution}
		\expression{P0}
	}
	\statemeasure{Mean tokens on place P1}{
		\estimator{mean variance distribution}
		\expression{P1}
	}
}
