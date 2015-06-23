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
   						DDS_KEY string id_entidade;
						string caminho_ontologia;
   						sequence<octet> rdfSerializado;
   						string tipo_serializacao;
						string tipo_inferencia;
					};
				};
			};
		};
	};
};
