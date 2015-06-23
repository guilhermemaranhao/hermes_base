/****************************************************************
 *
 *  file:  notificacao.ddl
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
				module hermes{
					struct NotificacaoType{
   						DDS_KEY string instancia_sujeito;
   						DDS_KEY string instancia_predicado;
   						DDS_KEY string instancia_objeto;
   						string tipo_sujeito;
   						string tipo_predicado;
   						string tipo_objeto;
   						sequence<octet> rdfSerializado;
   						string tipo_serializacao;
					};
				};
			};
		};
	};
};
