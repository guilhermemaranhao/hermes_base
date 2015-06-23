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
				module hermesbase{
					module coreDX{
						module notificacao{
							struct NotificacaoType{
   							DDS_KEY string id_entidade;
							string nome_topico;
							string complemento_topico;
							string caminho_ontologia;
   							sequence<octet> contexto;
   							string tipo_serializacao;
							};
   						};
					};
				};
			};
		};
	};
};
