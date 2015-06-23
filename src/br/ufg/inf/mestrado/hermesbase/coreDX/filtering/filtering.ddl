/****************************************************************
 *
 *  file:  filtering.ddl
 *  desc:  DDL for the Hermes Infrastructure
 * 
 ****************************************************************/
#ifdef DDS_IDL
#define DDS_KEY __dds_key
#else
#define DDS_KEY
#endif 

module br{
	module ufg{
		module inf{
			module mestrado{
				module hermesbase{
					module coreDX{
						module filtering{
							struct FilteringType{
							DDS_KEY string idListener;
							string nomeTopico;
							sequence<octet> filtroJson;
							string consultaFiltro;
							};
   						};
					};
				};
			};
		};
	};
};
