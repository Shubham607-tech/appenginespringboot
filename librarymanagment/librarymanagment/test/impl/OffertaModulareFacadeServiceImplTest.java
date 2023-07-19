package com.capgemini.app.ofm.core.facade.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.app.ofm.base.library.config.exception.OFMBusinessExceptionHandler;
import com.capgemini.app.ofm.base.library.config.exception.OFMIntegrationExceptionHandler;
import com.capgemini.app.ofm.base.library.dtos.SecurityContext;
import com.capgemini.app.ofm.core.dto.GenericAddProductDto;
import com.capgemini.app.ofm.core.dto.LDGruppoDto;
import com.capgemini.app.ofm.core.dto.LDProdottoDto;
import com.capgemini.app.ofm.core.dto.LeggiDatiInputDto;
import com.capgemini.app.ofm.core.dto.LeggiDatiResponseDto;
import com.capgemini.app.ofm.core.dto.LeggiStatoInputDto;
import com.capgemini.app.ofm.core.dto.LeggiStatoResponseDto;
import com.capgemini.app.ofm.core.dto.ProcessoDto;
import com.capgemini.app.ofm.core.dto.SoggettoDto;
import com.capgemini.app.ofm.core.dto.UdvSelezionatoDto;
import com.capgemini.app.ofm.core.dto.structureofm.ConfigurazioneOFMResponseDto;
import com.capgemini.app.ofm.core.dto.structureofm.GruppoDto;
import com.capgemini.app.ofm.core.dto.structureofm.ProdottoDto;
import com.capgemini.app.ofm.core.dto.structureofm.ProdottoSelezionatoDto;
import com.capgemini.app.ofm.core.dto.structureofm.StrutturaOffertaDto;
import com.capgemini.app.ofm.core.facade.TestConfigFacade;
import com.capgemini.app.ofm.core.facade.utilities.ProductServiceUtility;
import com.capgemini.app.ofm.core.facade.utilities.StrutturaOFMUtility;
import com.capgemini.app.ofm.core.services.common.enums.StatoOFM;
import com.capgemini.app.ofm.core.services.common.enums.TipoNdg;
import com.capgemini.app.ofm.core.services.common.enums.TipoOperazione;
import com.capgemini.app.ofm.core.services.common.enums.TipoProdottoOmnicanale;
import com.capgemini.app.ofm.core.services.domains.services.OffertaModulareService;
import com.capgemini.app.ofm.core.viewmodels.AggiungiProdottoInputVm;
import com.capgemini.app.ofm.core.viewmodels.AggiungiRimuoviUdvInputVm;
import com.capgemini.app.ofm.core.viewmodels.ConfigurazioneOFMResponseVm;
import com.capgemini.app.ofm.core.viewmodels.LeggiDatiInputVm;
import com.capgemini.app.ofm.core.viewmodels.LeggiDatiResponseVm;
import com.capgemini.app.ofm.core.viewmodels.LeggiStatoInputVm;
import com.capgemini.app.ofm.core.viewmodels.LeggiStatoResponseVm;
import com.capgemini.app.ofm.core.viewmodels.SoggettoVm;
import com.capgemini.app.ofm.core.viewmodels.TipoProdottoVm;
import com.capgemini.app.ofm.core.viewmodels.UdvDelProdottoVm;
import com.capgemini.app.ofm.core.viewmodels.client.TrasformaPrecontrattoOFMInputVm;
import com.capgemini.app.ofm.core.viewmodels.client.TrasformaPrecontrattoOFMResponseVm;
import com.capgemini.app.ofm.core.viewmodels.estinguiprecontratto.EstinguiPrecontrattoInputVm;
import com.capgemini.app.ofm.core.viewmodels.estinguiprecontratto.EstinguiPrecontrattoResponseVm;
import com.capgemini.integration.praticheprodotti.dtos.AggiornaDatiPraticaResponseTypeOutputDto;
import com.capgemini.integration.praticheprodotti.dtos.AggiornaDatiPraticaTypeInputDto;
import com.capgemini.integration.praticheprodotti.dtos.AnagraficaConsensoTypeInputDto;
import com.capgemini.integration.praticheprodotti.dtos.ElementoPraticaTypeInputDto;
import com.capgemini.integration.praticheprodotti.dtos.ListaElementoPraticaTypeInputDto;
import com.capgemini.integration.praticheprodotti.dtos.ListaProdottiTypeInput;
import com.capgemini.integration.praticheprodotti.dtos.PraticaAggiornamentoTypeInputDto;
import com.capgemini.integration.praticheprodotti.dtos.ProdottoTypeInputDto;
import com.capgemini.integration.praticheprodotti.dtos.RuoloProdottoTypeInput;
import com.capgemini.integration.praticheprodotti.dtos.TipoAnagraficaTypeInput;
import com.capgemini.integration.praticheprodotti.dtos.TipoProdottoTypeInput;
import com.capgemini.integration.praticheprodotti.service.impl.PraticheProdottiServiceImpl;
import com.capgemini.integration.prodotti.dtos.AddOrRemoveUdvInputDto;
import com.capgemini.integration.prodotti.dtos.VerifyAndAddOutputDto;

@SpringBootTest(classes = { TestConfigFacade.class })
class OffertaModulareFacadeServiceImplTest {

	@Autowired
	private OffertaModulareFacadeServiceImpl facadeService;

	@MockBean
	private OffertaModulareService service;

	@MockBean
	private StrutturaOFMUtility strutturaOFMUtility;

	@MockBean
	private ProductServiceUtility productServiceUtility;

	@MockBean
	private PraticheProdottiServiceImpl praticheProdottiServiceImpl;

	private static final String ANY_LAST_NAME = "Magno";

	private static final String ANY_USERNAME = "Carlo";

	private static final String ANY_BRANCHID = "00001";

	private static final String ANY_BANK = "05387";

	@Test
	void trasformaPrecontrattoTest() {
		TrasformaPrecontrattoOFMInputVm input = new TrasformaPrecontrattoOFMInputVm();
		input.setIdOFM(1L);
		TrasformaPrecontrattoOFMResponseVm output = facadeService.trasformaPrecontratto(input, new SecurityContext());

		assertNotNull(output);
		assertEquals(input.getIdOFM(), output.getIdOFM());
	}

	@Test
	void estinguiPrecontrattoTest() {

		EstinguiPrecontrattoInputVm inputVm = new EstinguiPrecontrattoInputVm();
		inputVm.setIdOFM(1L);
		EstinguiPrecontrattoResponseVm response = facadeService.estinguiPrecontratto(inputVm, new SecurityContext());
		assertNotNull(response);
		assertEquals(response.getIdOFM(), 1L);

	}

	@Test
	void recuperaStato() throws OFMIntegrationExceptionHandler {
		LeggiStatoInputVm input = new LeggiStatoInputVm();
		input.setIdOFM(1L);

		LeggiStatoResponseDto responseDto = new LeggiStatoResponseDto();
		responseDto.setStato(StatoOFM.BOZZA);
		Mockito.when(service.recuperaStato(Mockito.any(LeggiStatoInputDto.class))).thenReturn(responseDto);
		LeggiStatoResponseVm vm = facadeService.recuperaStato(input, new SecurityContext());
		assertEquals(StatoOFM.BOZZA, vm.getStato());
	}

	@Test
	void leggiDati() throws OFMIntegrationExceptionHandler {
		LeggiDatiInputVm input = new LeggiDatiInputVm();
		input.setIdOFM(1L);

		LeggiDatiResponseDto responseDto = new LeggiDatiResponseDto();
		responseDto.setIdOFM(1L);
		responseDto.setNdg("test");
		List<LDGruppoDto> listaGruppi = new ArrayList<LDGruppoDto>();
		LDGruppoDto gruppo = new LDGruppoDto();
		List<LDProdottoDto> listaProdotti = new ArrayList<LDProdottoDto>();
		LDProdottoDto prodotto = new LDProdottoDto();
		prodotto.setIdProdotto(1L);
		prodotto.setCodiceGruppo("A");
		prodotto.setCodiceProdottoFactory("factory");
		List<SoggettoDto> intestatariList = new ArrayList<SoggettoDto>();
		SoggettoDto soggetto = new SoggettoDto();
		soggetto.setNdg("ndg2");
		soggetto.setRuolo(TipoNdg.INT);
		intestatariList.add(soggetto);
		prodotto.setIntestatari(intestatariList);

		List<LDProdottoDto> sottoProdottiList = new ArrayList<LDProdottoDto>();
		LDProdottoDto prodotto1 = new LDProdottoDto();
		prodotto1.setIdProdotto(2L);

		List<SoggettoDto> intestatarioSottoProdList = new ArrayList<SoggettoDto>();
		SoggettoDto sogSotProd = new SoggettoDto();
		sogSotProd.setNdg("ndgSot");
		sogSotProd.setRuolo(TipoNdg.INT);
		intestatarioSottoProdList.add(sogSotProd);
		prodotto1.setIntestatari(intestatarioSottoProdList);

		sottoProdottiList.add(prodotto1);

		List<UdvSelezionatoDto> udvListSot = new ArrayList<UdvSelezionatoDto>();
		UdvSelezionatoDto udvSot = new UdvSelezionatoDto();
		udvSot.setCodice("udvSot");
		udvListSot.add(udvSot);
		prodotto1.setListaUdv(udvListSot);

		prodotto.setSottoProdotti(sottoProdottiList);
		List<UdvSelezionatoDto> udvList = new ArrayList<UdvSelezionatoDto>();
		UdvSelezionatoDto udv = new UdvSelezionatoDto();
		udv.setCodice("udv1");
		udvList.add(udv);
		prodotto.setListaUdv(udvList);
		listaProdotti.add(prodotto);
		gruppo.setListaProdotti(listaProdotti);
		listaGruppi.add(gruppo);
		responseDto.setListaGruppi(listaGruppi);
		when(service.leggiDati(Mockito.any(LeggiDatiInputDto.class))).thenReturn(responseDto);
		LeggiDatiResponseVm vm = facadeService.leggiDati(input, new SecurityContext());
		assertNotNull(vm);
		assertEquals(responseDto.getNdg(), vm.getNdg());
		assertNotNull(vm.getListaGruppi());
		assertEquals(1, vm.getListaGruppi().size());
		assertNotNull(vm.getListaGruppi().get(0));
		assertNotNull(vm.getListaGruppi().get(0).getListaProdotti());
		assertEquals(1, vm.getListaGruppi().get(0).getListaProdotti().size());
		assertNotNull(vm.getListaGruppi().get(0).getListaProdotti().get(0));
		assertEquals(1L, vm.getListaGruppi().get(0).getListaProdotti().get(0).getIdProdotto());

		assertEquals("ndg2", vm.getListaGruppi().get(0).getListaProdotti().get(0).getIntestatari().get(0).getNdg());
		assertEquals(TipoNdg.INT, vm.getListaGruppi().get(0).getListaProdotti().get(0).getIntestatari().get(0).getRuolo());

		assertEquals("A", vm.getListaGruppi().get(0).getListaProdotti().get(0).getCodiceGruppo());
		assertNotNull(vm.getListaGruppi().get(0).getListaProdotti().get(0).getListaUdv());
		assertEquals(1L, vm.getListaGruppi().get(0).getListaProdotti().get(0).getListaUdv().size());
		assertEquals("udv1", vm.getListaGruppi().get(0).getListaProdotti().get(0).getListaUdv().get(0).getCodice());

		assertEquals(2L, vm.getListaGruppi().get(0).getListaProdotti().get(0).getSottoProdotti().get(0).getIdProdotto());
		assertEquals("udvSot", vm.getListaGruppi().get(0).getListaProdotti().get(0).getSottoProdotti().get(0).getListaUdv().get(0).getCodice());

		assertEquals("ndgSot", vm.getListaGruppi().get(0).getListaProdotti().get(0).getSottoProdotti().get(0).getIntestatari().get(0).getNdg());

	}

	/*
	 * @Test void aggiungiRimuoviUdv() throws OFMIntegrationExceptionHandler {
	 * AggiungiRimuoviUdvInputVm input = assignAggiungiRimoviUdv();
	 * input.setOperazione(OperazioneType.AGGIUNTA);
	 *
	 * getUdvDelProdottoVm(input);
	 *
	 * ConfigurazioneOFMResponseVm response = createResponseVm();
	 *
	 * OggettoUdvSelezionatoVm object1=new OggettoUdvSelezionatoVm();
	 * object1.setCodice("object1");
	 *
	 * //response.setStrutturaOfferta(oggettoStrutturaOffertaVm);
	 * when(strutturaOFMUtility.getConfigurazione(Mockito.any(Long.class))).
	 * thenReturn(response);
	 *
	 * AddOrRemoveUdvInputDto addOrRemoveUdvInputDto = new AddOrRemoveUdvInputDto();
	 * TipoProdottoVm tipoProdottoVm = new TipoProdottoVm();
	 * when(productServiceImpl.addOrRemoveUDV(addOrRemoveUdvInputDto,
	 * tipoProdottoVm, new SecurityContext())).thenReturn(true);
	 * ConfigurazioneOFMResponseVm result = facadeService.aggiungiRimuoviUdv(input,
	 * new SecurityContext()); assertNotNull(result); }
	 */


	@Test
	void aggiungiRimuoviUdvTest() throws OFMIntegrationExceptionHandler, OFMBusinessExceptionHandler {
		AggiungiRimuoviUdvInputVm input = assignAggiungiRimoviUdv();
		input.setOperazione(TipoOperazione.RIMUOVI);

		getUdvDelProdottoVm(input);

		ConfigurazioneOFMResponseDto response = createResponseDto();

		UdvSelezionatoDto object1 = new UdvSelezionatoDto();
		object1.setCodice("object1");

		List<UdvSelezionatoDto> listudv = Arrays.asList(object1);

		StrutturaOffertaDto oggettoStrutturaOffertaDto = getStrtturaOffertaDto(listudv);

		response.setStrutturaOfferta(oggettoStrutturaOffertaDto);
		when(strutturaOFMUtility.getConfigurazione(Mockito.any(Long.class))).thenReturn(response);

		AddOrRemoveUdvInputDto addOrRemoveUdvInputDto = new AddOrRemoveUdvInputDto();
		when(productServiceUtility.addOrRemoveUDV(addOrRemoveUdvInputDto, TipoProdottoOmnicanale.ACCOUNT, new SecurityContext())).thenReturn(true);
		ConfigurazioneOFMResponseVm  result = facadeService.aggiungiRimuoviUdv(input, new SecurityContext());
		assertNotNull(result);

	}

	@Disabled // FIXME
	@Test
	void aggiungiProdotoTest() throws OFMIntegrationExceptionHandler {
		AggiungiProdottoInputVm input=new AggiungiProdottoInputVm();

		input.setIdOFM(1L);
		List<UdvDelProdottoVm> udvs=new ArrayList<>();

		UdvDelProdottoVm udv=new UdvDelProdottoVm();
		udv.setIdProdotto(1L);
		udvs.add(udv);
		input.setUdvDelProdotto(udvs);
		ConfigurazioneOFMResponseDto response = createResponseDto();

		UdvSelezionatoDto object1 = new UdvSelezionatoDto();
		object1.setCodice("object1");

		List<UdvSelezionatoDto> listudv = Arrays.asList(object1);

		StrutturaOffertaDto oggettoStrutturaOffertaDto = getStrtturaOffertaDto(listudv);

		response.setStrutturaOfferta(oggettoStrutturaOffertaDto);

		when(strutturaOFMUtility.getConfigurazione(Mockito.any(Long.class))).thenReturn(response);

		VerifyAndAddOutputDto output=new VerifyAndAddOutputDto();


		GenericAddProductDto verifyIput = null;
		TipoProdottoOmnicanale tipoProdotto = null;
		when(productServiceUtility.verifyAndAddProduct(verifyIput, tipoProdotto, new SecurityContext())).thenReturn(output);

		AddOrRemoveUdvInputDto addOrRemoveUdvInputDto = new AddOrRemoveUdvInputDto();

		when(productServiceUtility.addOrRemoveUDV(addOrRemoveUdvInputDto, tipoProdotto, new SecurityContext())).thenReturn(true);

		when(praticheProdottiServiceImpl.aggiornaDatiPratica(createPraticheInput(),createSecurityContext())).thenReturn(new AggiornaDatiPraticaResponseTypeOutputDto());

		ConfigurazioneOFMResponseVm result=facadeService.aggiungiProdotto(input, createSecurityContext());
		assertNotNull(result);
		assertNotNull(result.getStrutturaOfferta());
		assertNotNull(result.getStrutturaOfferta().getListaGruppi());
		assertNotNull(result.getStrutturaOfferta().getListaGruppi().get(0).getListaProdotti());
		// assertNotNull(result.getStrutturaOfferta().getListaGruppi().get(0).getListaProdotti().get(0).getIdProdotto());
		// assertEquals(result.getStrutturaOfferta().getListaGruppi().get(0).getListaProdotti().get(0).getIdProdotto(), 1L);

	}

	@Disabled // FIXME re enable the test
	@Test
	void aggiungiProdotoTestUDVListEmpty() throws OFMIntegrationExceptionHandler, SQLException {
		AggiungiProdottoInputVm input=new AggiungiProdottoInputVm();

		input.setIdOFM(1L);

		ConfigurazioneOFMResponseDto response = createResponseDto();
		StrutturaOffertaDto oggettoStrutturaOffertaDto = addStructureToResponseEmptyUdv();
		response.setStrutturaOfferta(oggettoStrutturaOffertaDto);

		when(strutturaOFMUtility.getConfigurazione(Mockito.any(Long.class))).thenReturn(response);

		VerifyAndAddOutputDto output=new VerifyAndAddOutputDto();

		GenericAddProductDto verifyIput = null;
		TipoProdottoOmnicanale tipoProdotto = null;
		when(productServiceUtility.verifyAndAddProduct(verifyIput, tipoProdotto, new SecurityContext())).thenReturn(output);

		doNothing().when(service).updateProcesso(new ProcessoDto(), TipoOperazione.AGGIUNGI);

		when(praticheProdottiServiceImpl.aggiornaDatiPratica(any(AggiornaDatiPraticaTypeInputDto.class), any())).thenReturn(new AggiornaDatiPraticaResponseTypeOutputDto());

		ConfigurazioneOFMResponseVm result=facadeService.aggiungiProdotto(input, createSecurityContext());

		assertNotNull(result);
		// FIXME the method under this task should be updated with the new versione
		// assertNotNull(result.getTipoProdotto());
		// assertNotNull(result.getStrutturaOfferta().getListaGruppi().get(0).getListaProdotti());
		// assertNull(result.getStrutturaOfferta().getListaGruppi().get(0).getListaProdotti().get(0).getUdvList());
	}

	private StrutturaOffertaDto addStructureToResponseEmptyUdv() {
		StrutturaOffertaDto oggettoStrutturaOffertaVm = new StrutturaOffertaDto();
		List<GruppoDto> listaGruppi = new ArrayList<>();
		GruppoDto oggoetoVm = new GruppoDto();
		List<ProdottoDto> listaProdoti = new ArrayList<ProdottoDto>();
		listaProdoti.add(new ProdottoDto());
		oggoetoVm.setListaProdotti(listaProdoti);
		listaGruppi.add(oggoetoVm);
		oggettoStrutturaOffertaVm.setListaGruppi(listaGruppi);
		return oggettoStrutturaOffertaVm;
	}

	@Disabled // FIXME re enable the test
	@Test
	void aggiungiProdotoTestEmptyInput() throws OFMIntegrationExceptionHandler {
		AggiungiProdottoInputVm input=new AggiungiProdottoInputVm();


		ConfigurazioneOFMResponseDto response = createResponseDto();

		UdvSelezionatoDto object1 = new UdvSelezionatoDto();
		object1.setCodice("object1");

		List<UdvSelezionatoDto> listudv = Arrays.asList(object1);

		StrutturaOffertaDto oggettoStrutturaOffertaDto = getStrtturaOffertaDto(listudv);

		response.setStrutturaOfferta(oggettoStrutturaOffertaDto);
		when(strutturaOFMUtility.getConfigurazione(Mockito.any(Long.class))).thenReturn(response);

		VerifyAndAddOutputDto output=new VerifyAndAddOutputDto();
		TipoProdottoVm tipo=new TipoProdottoVm();

		GenericAddProductDto verifyIput = null;
		TipoProdottoOmnicanale tipoProdotto = null;
		when(productServiceUtility.verifyAndAddProduct(verifyIput, tipoProdotto, new SecurityContext())).thenReturn(output);

		AddOrRemoveUdvInputDto addOrRemoveUdvInputDto = new AddOrRemoveUdvInputDto();

		when(productServiceUtility.addOrRemoveUDV(addOrRemoveUdvInputDto, tipoProdotto, new SecurityContext())).thenReturn(true);

		when(praticheProdottiServiceImpl.aggiornaDatiPratica(any(AggiornaDatiPraticaTypeInputDto.class), any())).thenReturn(new AggiornaDatiPraticaResponseTypeOutputDto());
		// FIXME no exception is thrown but the method need to be changed
		org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class,()->facadeService.aggiungiProdotto(input, createSecurityContext()));

	}

	private AggiornaDatiPraticaTypeInputDto createPraticheInput() {
		AggiornaDatiPraticaTypeInputDto praticheInputDto=new AggiornaDatiPraticaTypeInputDto();
		PraticaAggiornamentoTypeInputDto inputDto=new PraticaAggiornamentoTypeInputDto();
		inputDto.setIdentificativoPratica("900001571");
		ListaElementoPraticaTypeInputDto listInputDto=new ListaElementoPraticaTypeInputDto();
		List<ElementoPraticaTypeInputDto> list=new ArrayList<>();
		ElementoPraticaTypeInputDto elementa=new ElementoPraticaTypeInputDto();
		AnagraficaConsensoTypeInputDto anagrafica=new AnagraficaConsensoTypeInputDto();
		ListaProdottiTypeInput propdti=new ListaProdottiTypeInput();
		propdti.setNumeroElementiTotali(new BigInteger("1"));
		List<ProdottoTypeInputDto> prodotto=new ArrayList<>();
		ProdottoTypeInputDto prodottoTypeInputDto=new ProdottoTypeInputDto();
		prodottoTypeInputDto.setTipoProdotto(TipoProdottoTypeInput.NEGOZIAZIONE_E_CONSULENZA);
		prodottoTypeInputDto.setRuoloProdotto(RuoloProdottoTypeInput.INTESTATARIO);
		prodottoTypeInputDto.setIdentificativoProdotto("131ffd74-1167-42f3-af3a-2d895e7ecaf7");
		prodottoTypeInputDto.setFlagEscludiValutazione(true);
		prodotto.add(prodottoTypeInputDto);
		propdti.setProdotto(prodotto);
		anagrafica.setProdotti(propdti);
		anagrafica.setIdentificativoAnagrafica("17789644");
		anagrafica.setIntestatario(true);
		anagrafica.setTipoAnagrafica(TipoAnagraficaTypeInput.CLIENTE);
		elementa.setAnagrafica(anagrafica);
		// TODO Mapping not clear
		list.add(elementa);
		listInputDto.setElementoPratica(list);
		listInputDto.setNumeroElementiTotali(new BigInteger("1"));
		inputDto.setElementiPratica(listInputDto);
		praticheInputDto.setPratica(inputDto);
		return praticheInputDto;
	}

	private void createUdvList(final AggiungiProdottoInputVm input) {
		List<UdvDelProdottoVm> listaUdv=new ArrayList<>();
		UdvDelProdottoVm udvObject=new UdvDelProdottoVm();
		udvObject.setCodice("12311");
		udvObject.setDescrizione("ss");
		udvObject.setIdProdotto(1L);
		udvObject.setTipoAzione("sasas");
		// udvObject.setListino(new ListinoVm());
		listaUdv.add(udvObject);
		input.setUdvDelProdotto(listaUdv);
	}

	private List<SoggettoVm> createSoggetoList() {
		List<SoggettoVm> listaSoggeto=new ArrayList<>();
		listaSoggeto.add(createSoggetoVm("u122121", TipoNdg.INT));
		listaSoggeto.add(createSoggetoVm("u12331", TipoNdg.COINT));
		listaSoggeto.add(createSoggetoVm("u1232231", TipoNdg.DEL));
		return listaSoggeto;
	}

	private SoggettoVm createSoggetoVm(final String string, final TipoNdg ndgType) {
		SoggettoVm oggetoSoggetoVm=new SoggettoVm();
		oggetoSoggetoVm.setNdg(string);
		oggetoSoggetoVm.setRuolo(ndgType);
		return oggetoSoggetoVm;
	}


	private AggiungiRimuoviUdvInputVm assignAggiungiRimoviUdv() {
		AggiungiRimuoviUdvInputVm input = new AggiungiRimuoviUdvInputVm();
		input.setIdOFM(1L);
		input.setIdProdottoOFM(1L);
		return input;
	}

	private StrutturaOffertaDto getStrtturaOffertaDto(final List<UdvSelezionatoDto> listudv) {
		StrutturaOffertaDto oggettoStrutturaOffertaVm = new StrutturaOffertaDto();
		List<GruppoDto> grouppillsit = new ArrayList<GruppoDto>();
		grouppillsit.add(getGruppoDto(listudv));
		oggettoStrutturaOffertaVm.setListaGruppi(grouppillsit);
		return oggettoStrutturaOffertaVm;
	}

	private GruppoDto getGruppoDto(final List<UdvSelezionatoDto> listudv) {
		GruppoDto dto = new GruppoDto();
		dto.setDescrizione("sddsdsdsdsdsd");
		dto.setCodice("sdsdsdsooooo");

		List<ProdottoDto> prodotolist = getProdotoDto(listudv);

		dto.setListaProdotti(prodotolist);
		return dto;
	}

	private List<ProdottoDto> getProdotoDto(final List<UdvSelezionatoDto> listudv) {
		List<ProdottoDto> prodotolist = new ArrayList<>();
		ProdottoDto prodottoDto = new ProdottoDto();
		List<ProdottoSelezionatoDto> prodottiSelezionati = new ArrayList<ProdottoSelezionatoDto>();
		ProdottoSelezionatoDto prodSel = new ProdottoSelezionatoDto();
		prodSel.setIdProdotto(1L);
		prodSel.setUdvList(listudv);
		prodottiSelezionati.add(prodSel);
		prodottoDto.setProdottiSelezionati(prodottiSelezionati);
		prodottoDto.setPrezzo(10D);
		prodottoDto.setPrezzoScontato(10D);
		prodottoDto.setTipoProdotto(TipoProdottoOmnicanale.CREDITCARD);
		prodotolist.add(prodottoDto);
		return prodotolist;
	}

	private void getUdvDelProdottoVm(final AggiungiRimuoviUdvInputVm input) {

		input.setCodiceUdvUds("test");
	}

	private ConfigurazioneOFMResponseDto createResponseDto() {
		ConfigurazioneOFMResponseDto response = new ConfigurazioneOFMResponseDto();
		response.setIdOggettoInterno(new Long(1));
		response.setPrezzoTotaleLordo(9D);
		response.setPrezzoTotaleScontato(9D);

		return response;
	}

	private SecurityContext createSecurityContext() {
		SecurityContext sc = new SecurityContext();
		sc.setUserName(ANY_USERNAME);
		sc.setUserSurname(ANY_LAST_NAME);
		sc.setBanca(ANY_BANK);
		sc.setInternalBranch(ANY_BRANCHID);
		sc.setUtente("810011");
		return sc;
	}

}
