---
title: Rastreamento Correios — códigos BR e Minhas Importações
description: Rastreie encomendas dos Correios no Brasil e no exterior: códigos de 13 caracteres, status, Remessa Conforme, Minhas Importações, prazos de guarda e SAC.
slug: rastreamento-correios-brasil
published: 2026-11-23
updated: 2026-11-23
---

Os **Correios** (*Empresa Brasileira de Correios e Telégrafos*) são a empresa pública postal do Brasil. Encomendas postadas no Brasil mantêm o mesmo código dos Correios até o destino, sendo entregues no exterior pelos correios locais; da mesma forma, compras vindas de fora mantêm o código do correio de origem e são entregues pelos Correios no Brasil. Você pode consultar até 20 códigos (ou pesquisar por CPF/CNPJ, com verificação por imagem) no [Rastreamento dos Correios](https://rastreamento.correios.com.br/app/index.php) ou colar o código no campo acima.

## Formato do código de rastreamento dos Correios

Os códigos dos Correios seguem o padrão S10 da União Postal Universal (UPU), com **13 caracteres**: duas letras que identificam o serviço, nove dígitos (sendo o nono um dígito verificador) e duas letras do país de origem (`BR` para o Brasil). Exemplos com dígitos inventados:

| Código (inventado) | O que indica |
| --- | --- |
| `EA482917367BR` | `E`: EMS, serviço expresso internacional. |
| `CA482917367BR` | `C`: encomenda postal internacional. |
| `LA482917367BR` | `L`: remessa rastreável de pequeno porte. |
| `RA482917367BR` | `R`: correspondência ou pequeno pacote registrado. |
| `NN482917367BR` | `N`, `B`, `D`, `P` ou `Z` (além de `AV`–`AZ`, `GA`, `GD`): letras que a UPU reserva a serviços nacionais ou bilaterais. |
| `RA482917367CN` | Código emitido pelo correio da China (China Post), mas entregue no Brasil pelos Correios sob o mesmo código. |

O nono dígito confere os oito anteriores, por isso um único número trocado geralmente torna o código inválido. Os Correios informam que algumas encomendas vindas do exterior também têm código terminado em `BR`, de um serviço específico dos Correios. Sem código? Peça diretamente à loja ou ao remetente. O rastreador oficial mantém o histórico online por **180 dias** ([Perguntas frequentes dos Correios](https://www.correios.com.br/acesso-a-informacao/perguntas-frequentes)). Veja também o guia de [formatos de código de rastreio](guide:tracking-number-formats).

## O que significam os status dos Correios

Os Correios exibem cada movimentação em português, acompanhada da cidade e da unidade da federação (UF):

| Status nos Correios | O que significa |
| --- | --- |
| `Etiqueta emitida` | A etiqueta foi gerada pelo remetente; os Correios ainda não receberam o objeto físico. |
| `Objeto postado` | Objeto entregue aos Correios e já em posse da empresa. |
| `Objeto encaminhado para fiscalização aduaneira de exportação` | Encomenda enviada ao exterior em conferência na alfândega brasileira antes do embarque; enquanto estiver ali, não fica sob guarda dos Correios. |
| `Objeto em transferência - por favor aguarde` | Em deslocamento entre unidades operacionais ou centros de tratamento dos Correios. |
| `Objeto recebido pelos Correios do Brasil` | Encomenda vinda do exterior chegou à unidade internacional dos Correios no Brasil. |
| `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes` | Dados eletrônicos enviados à Receita Federal e a eventuais órgãos anuentes (como ANVISA, ANEEL, MAPA, Exército ou Polícia Federal). |
| `Aguardando pagamento` | Há tributos ou despacho postal pendentes em Minhas Importações antes da liberação. |
| `Análise concluída - importação autorizada` | Fiscalização encerrada e entrada no país autorizada. |
| `Saída do Centro Internacional` | A encomenda saiu do centro internacional rumo à distribuição doméstica no Brasil. |
| `Objeto saiu para entrega ao destinatário` | O carteiro está em rota para entrega no endereço informado. |
| `Objeto não entregue - carteiro não atendido` | Tentativa de entrega sem ninguém disponível para receber. |
| `Objeto aguardando retirada no endereço indicado` | Disponível para retirada presencial na agência ou unidade indicada no rastreamento. |
| `Inconsistências no endereçamento do objeto` | Problema identificado no endereço ou CEP da etiqueta. |
| `Objeto entregue ao destinatário` | Entregue ao destinatário. |

Algumas mensagens exigem atenção imediata: `Importação não autorizada` indica recusa pela autoridade aduaneira; `Prazo de regularização encerrado`, que o prazo para regularizar ou pagar expirou; `Devolução determinada pela autoridade competente`, que os órgãos determinaram retorno à origem; e `Objeto saiu para entrega ao remetente` significa devolução ao remetente, não entrega a você. Enquanto aparecer `Em fiscalização aduaneira`, a encomenda está sob responsabilidade da Receita Federal, não dos Correios.

## Compras internacionais: CPF, Remessa Conforme e Minhas Importações

Os detalhes oficiais constam em [Recebimento de Encomendas Internacionais](https://www.correios.com.br/receber/encomenda/internacional) e nos manuais da Receita Federal ([Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao)):

- **CPF obrigatório:** Toda encomenda vinda do exterior é uma importação e exige CPF regular do destinatário, com nome idêntico ao cadastro da Receita Federal.
- **Minhas Importações:** Todo o acompanhamento aduaneiro, envio de documentos e pagamento ocorre pelo portal `portalimportador.correios.com.br` ou pelo aplicativo Correios, depois de um cadastro único no Meu Correios.
- **Imposto de Importação (regra geral):** **60%** sobre o valor aduaneiro (mercadoria + frete + seguro) para compras em lojas fora do Programa Remessa Conforme ou importações destinadas a pessoa jurídica.
- **Sites certificados no [Programa Remessa Conforme (PRC)](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) (pessoa física):** **0%** até US$ 50; acima de US$ 50, **60%** com desconto de US$ 30 no imposto (regra válida para declarações registradas a partir de 12 de maio de 2026; anteriormente era 20% até US$ 50 e desconto de US$ 20 acima).
- **ICMS e Despacho Postal:** Incide ICMS estadual (17% a 20%, conforme o estado), dentro ou fora do PRC, podendo somar-se a tarifa de **Despacho Postal** cobrada pelos Correios pelos serviços de suporte aduaneiro (que não é imposto). Acima de US$ 3.000 ou em retenções especiais, é preciso contratar despachante aduaneiro.
- **Prazo para pagar e revisão:** Em sites do PRC, os tributos são recolhidos no checkout; se não forem cobrados na compra, serão lançados na chegada sem o benefício. Quando cobrados na chegada, o pagamento via boleto ou Pix em Minhas Importações deve ser feito em até **20 dias corridos**, ou a encomenda volta ao exterior. Caso discorde do valor, solicite **Revisão de Tributos** no portal antes de gerar o boleto ou Pix (documentos só podem ser enviados uma vez).
- **Retirada na agência e prazos de guarda:** Segundo a página de [Prazo de Guarda de Objetos Internacionais](https://www.correios.com.br/receber/prazo-de-guarda-objetos-internacionais), encomendas internacionais aguardam retirada por **20 dias corridos** após as tentativas de entrega (**7 dias** para EMS e Correios Packet). Terceiros podem retirar levando autorização assinada, documento original próprio e documento (ou cópia) do destinatário.
- **Cuidado com golpes:** Conforme o alerta de [Segurança dos Correios](https://www.correios.com.br/falecomoscorreios/seguranca/seguranca), os Correios nunca enviam links de pagamento nem pedem dados bancários por e-mail, SMS ou WhatsApp, e a Receita Federal não envia boletos nem liga cobrando taxas. Acesse `correios.com.br` digitando o endereço diretamente — veja também o guia de [SMS falsos de entrega](guide:delivery-scam-texts).

Se sua compra no Brasil vier por transportadora privada em vez dos Correios, confira também [Jadlog](carrier:jadlog), [Anjun Logistics](carrier:anjun), [J&T Express Brazil](carrier:j-and-t-br) e [Memphis Courier](carrier:memphis-courier).

## Encomendas do Brasil para o exterior, atrasos e reclamações

Encomendas enviadas do Brasil para fora passam pela alfândega brasileira de exportação e seguem com o mesmo código S10 para os correios do país de destino (como USPS, Royal Mail, CTT, La Poste, Correos ou Deutsche Post). Na União Europeia, compras online de fora do bloco até € 150 pagam direito aduaneiro de € 3 por categoria de item (desde 1º de julho de 2026) e taxa de manuseio de € 2 por item independentemente do valor (desde 1º de novembro de 2026) — detalhes no [guia de alfândega](guide:customs).

- **Código não aparece:** Confirme letras e dígitos com o remetente e verifique se já houve postagem; se sim, o remetente abre manifestação.
- **Parado na alfândega ou atrasado:** Os Correios não estipulam prazo para análise da Receita Federal; acompanhe Minhas Importações e veja [por que o rastreamento para de atualizar](guide:tracking-not-updating). Se a previsão de entrega vencer após o pagamento, registre uma manifestação no [Fale Conosco](https://faleconosco.correios.com.br/inicio).
- **Extravio, avaria ou devolução:** Reclamações são feitas pelo Fale Conosco com conta Meu Correios pelo remetente ou por quem gerou o boleto/Pix dos tributos. Em objetos internacionais, o prazo corre a partir da postagem: **120 dias** para EMS e serviços expressos e **6 meses** para os demais; em importações Correios Packet, **90 dias** após entrega ou devolução (30 dias para avaria). O retorno padrão dos Correios é em até 5 dias úteis e a indenização é paga apenas em conta bancária brasileira — saiba mais em [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Como acompanhar Correios Brazil no Peek

O Peek identifica **Correios Brazil** na hora para duas letras, nove dígitos e `BR` quando o dígito verificador está certo. Com um dígito errado, o Peek não indica transportadora nenhuma e, se você escolher Correios Brazil com "Trocar", avisa que Correios Brazil "não emite códigos como este": confira o código. Links de rastreamento dos Correios com `objetos=` também funcionam.

O Peek busca o histórico dos Correios pelo [adaptador Correios Brazil no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correios-br) e mostra o status, cada movimentação com cidade e UF e a data prevista de entrega enquanto o objeto está em trânsito e dentro do prazo. Os horários são lidos no fuso que os Correios dão a cada movimentação no Brasil, e no relógio do país que emitiu o código para as movimentações do correio de origem, e aparecem no fuso do seu aparelho. Os status ficam em português.

Os Correios não informam ao Peek o destino da encomenda; por isso, fora do Brasil, o Peek também consulta o correio do seu país (USPS, Royal Mail, Canada Post, An Post ou Australia Post, por exemplo) com o mesmo código e passa para ele quando este mostra uma movimentação não mais antiga que a mais recente dos Correios, mantendo o histórico dos Correios. Uma encomenda que chega ao Brasil com código de outro correio é acompanhada nesse correio; o Peek também consulta os Correios quando esse correio indica o Brasil como destino (ou, se não indicar nenhum, quando você está no Brasil).

O Peek traduz os eventos para etapas claras: **Etiqueta criada** (`Etiqueta emitida`), **Recebida pela transportadora** (`Objeto postado`), **Em trânsito** (transferências e saída do centro internacional), **Na alfândega** (fiscalização aduaneira e `Aguardando pagamento`), **Saiu para entrega**, **Tentativa de entrega**, **Pronta para retirada** (`Objeto aguardando retirada no endereço indicado`), **Entregue** e **Devolvida ao remetente**.

Quando o registro mais recente tem quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com o dia desse registro, por exemplo "Sem atualizações desde seg 12 out". A página da encomenda mostra então "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Correios Brazil.", com o link "Pedir ajuda a Correios Brazil" para a Central de Atendimento dos Correios. Depois de três semanas sem registro novo, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Correios Brazil."

Com a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta os Correios até a cada 10 minutos; no dia em que os Correios registram `Objeto saiu para entrega ao destinatário`, a cada 2 minutos (a cada 5 quando precisa abrir o rastreador dos Correios num navegador) a partir das 6h no relógio da movimentação mais recente, enquanto o rastreio dos próprios Correios responder. Fora disso, de hora em hora. Depois de 12 horas só com "Etiqueta criada" ou "Recebida pela transportadora", ou de dois dias sem novidade, consulta de hora em hora em qualquer caso, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreio dos Correios não responde e o Peek acompanha a encomenda só por serviços de rastreamento, consulta de hora em hora, a cada 3 horas depois de um dia sem novidade e até a cada 15 minutos no dia em que a encomenda sai para entrega. Nos alertas, escolha entre "Cada movimentação", "Etapas importantes" ou "Só a entrega".

## Telefones e atendimento oficial dos Correios

Canais publicados na [Central de Atendimento dos Correios](https://www.correios.com.br/falecomoscorreios/central-de-atendimento):

- **Telefone (SAC):** `4003 8210` (capitais, regiões metropolitanas e celulares de demais localidades) e `0800 881 8210` (telefones fixos das demais localidades), de segunda a sexta das 8h às 20h e sábados das 8h às 14h (exceto feriados)
- **WhatsApp:** `(11) 4003-8210` (também atende consultas de rastreamento)
- **Chat:** assistente virtual Carol em `correios.com.br`
- **Reclamações e manifestações:** portal [Fale Conosco](https://faleconosco.correios.com.br/inicio) (centros de distribuição e triagem não recebem ligações)

## Perguntas frequentes

### O que significa "Objeto em transferência - por favor aguarde"?

Significa que a encomenda está viajando entre duas unidades operacionais dos Correios no Brasil e não exige nenhuma ação sua no momento.

### Por que meu rastreamento mostra "Aguardando pagamento"?

Há tributos aduaneiros ou tarifa de Despacho Postal em aberto. Acesse Minhas Importações (`portalimportador.correios.com.br`) ou o app Correios para pagar por boleto ou Pix em até **20 dias corridos**, evitando a devolução ao exterior.

### Quanto tempo os Correios guardam uma encomenda internacional na agência?

Após as tentativas de entrega sem sucesso, objetos internacionais ficam guardados por **20 dias corridos** na agência indicada, ou **7 dias corridos** para envios EMS e Correios Packet.

### Posso rastrear uma encomenda dos Correios enviada para fora do Brasil?

Sim. O código de 13 caracteres terminado em `BR` continua o mesmo no rastreador dos Correios e no site dos correios do país de destino assim que o pacote chega lá.

:::sources
- [Correios — Rastreamento](https://rastreamento.correios.com.br/app/index.php)
- [Correios — Recebimento de Encomendas Internacionais](https://www.correios.com.br/receber/encomenda/internacional)
- [Correios — Prazo de Guarda de Objetos Internacionais](https://www.correios.com.br/receber/prazo-de-guarda-objetos-internacionais)
- [Correios — Perguntas frequentes](https://www.correios.com.br/acesso-a-informacao/perguntas-frequentes)
- [Correios — Central de Atendimento](https://www.correios.com.br/falecomoscorreios/central-de-atendimento)
- [Correios — Fale Conosco](https://faleconosco.correios.com.br/inicio)
- [Correios — Segurança](https://www.correios.com.br/falecomoscorreios/seguranca/seguranca)
- [Receita Federal — Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao)
- [Receita Federal — Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc)
- [Adaptador Correios Brazil no universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/correios-br)
:::
