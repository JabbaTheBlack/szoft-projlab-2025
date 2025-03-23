package hu.bme;

import java.util.Scanner;

import hu.bme.tests.TestGombaFonalElvagasVanSporaHatas;
import hu.bme.tests.TestGombaFonalElvagasaNincsSporaHatas;
import hu.bme.tests.TestGombaFonalNovesztesMultiTypeTektonon;
import hu.bme.tests.TestGombaFonalNoveszteseSingleTypeTektonon;
import hu.bme.tests.TestGombaFonalFelszivodasa;
import hu.bme.tests.TestGombaTipusValasztas;
import hu.bme.tests.TestGombatestElhalSporaSzetszorasUtan;
import hu.bme.tests.TestGombatestFejleszteseSporaszintEleg;
import hu.bme.tests.TestGombatestFejleszteseSporaszintNemEleg;
import hu.bme.tests.TestRovarAtlepMasikTektonra;
import hu.bme.tests.TestRovarElfogyasztSporat;
import hu.bme.tests.TestGombaFonalNovesztesNemSzomszedosTekton;
import hu.bme.tests.TestGombafonalElszakadasTektonToresnel;
import hu.bme.tests.TestSporaGyorsitjaFonalNovekedest;
import hu.bme.tests.TestSporaKiloveseSzomszedosTektonra;
import hu.bme.tests.TestSporeKiloveseSzomszeddalSzomszedosTektonra;
import hu.bme.tests.TestTektonNemTorhetGombaTestMiatt;
import hu.bme.tests.TestTektonToresSzomszedokFrissitese;
import hu.bme.tests.TestTobbHatasARovaron;
import hu.bme.tests.TestUjGombatestNemNoHaMarVan;
import hu.bme.tests.TestUjGombatestNoveszteseSikeres;
import hu.bme.tests.TestUjGombatestSikeretelenNincsSpora;

/**
 * A class responsible for running various test cases related to fungal networks and interactions.
 */

public class TestRunner {

    /**
     * Runs a series of test cases based on user input.
     * 
     * This method presents a menu to the user, allowing them to select which test case to run.
     * It continues to prompt the user until they choose to exit.
     */
    public void runTests() {
        Scanner scanner = new Scanner(System.in);  
        int choice = 0;
        do {
            printTestOptions();
            System.out.print("Adja meg a teszteset számát: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 :
                    TestGombaFonalElvagasaNincsSporaHatas.test_gombafonal_elvagasa_nincs_spora_hatas();
                    waitForEnter(scanner);
                    break;
                case 2 : 
                    TestGombaFonalElvagasVanSporaHatas.test_gombafonal_elvagasa_van_spora_hatas();
                    waitForEnter(scanner);
                    break; 
                case 3 :
                    TestRovarAtlepMasikTektonra.test_rovar_atlep_masik_tektonra(scanner);
                    waitForEnter(scanner);
                    break;
                case 4: 
                    TestGombaFonalNovesztesMultiTypeTektonon.test_gombafonal_novesztes_multi_type_tektonon();
                    waitForEnter(scanner);
                    break;
                case 5:
                    TestGombaFonalNoveszteseSingleTypeTektonon.test_gombafonal_novesztese_single_type_tektonon();
                    waitForEnter(scanner);
                    break;
                case 6 :
                    TestRovarElfogyasztSporat.test_rovar_elfogyaszt_sporat_es_megkapja_hatasat();
                    waitForEnter(scanner);
                    break; 
                case 7 :
                    TestGombaFonalNovesztesNemSzomszedosTekton.TestGombaFonalNovesztesNemSzomszedosTekton();
                    waitForEnter(scanner);
                    break;
                case 8 :
                    TestGombaFonalFelszivodasa.test_gombafonal_felszivodasa();
                    waitForEnter(scanner);
                    break;
                case 9 :
                    TestGombafonalElszakadasTektonToresnel.TestGombafonalElszakadasTektonToresnel();
                    waitForEnter(scanner);
                    break;
                case 10: 
                    TestTektonToresSzomszedokFrissitese.test_tekton_tores_szomszedok_frissitese();
                    waitForEnter(scanner);
                    break;
                case 11:
                    TestUjGombatestNoveszteseSikeres.test_uj_gombatest_novesztese_sikeres();
                    waitForEnter(scanner);
                    break;
                case 12: 
                    TestUjGombatestSikeretelenNincsSpora.test_uj_gombatest_novesztese_sikertelen_nincs_spora();
                    waitForEnter(scanner);
                    break;
                case 13 : 
                    TestUjGombatestNemNoHaMarVan.test_uj_gombatest_nem_no_ha_mar_van();
                    waitForEnter(scanner);
                    break;
                case 14:
                    TestSporaKiloveseSzomszedosTektonra.test_spora_kilovese_szomszedos_tektonra();
                    waitForEnter(scanner);
                    break;
                case 15:
                    TestSporeKiloveseSzomszeddalSzomszedosTektonra.test_spora_kilovese_szomszeddal_szomszedos_tektonra();
                    waitForEnter(scanner);
                    break;
                case 16: 
                    TestSporaGyorsitjaFonalNovekedest.test_spora_gyorsitja_fonal_novekedest();
                    waitForEnter(scanner);
                    break;
                case 17:
                    TestTektonNemTorhetGombaTestMiatt.test_tekton_nem_torhet_gombatest_miatt();
                    waitForEnter(scanner);
                    break;
                case 18:
                    TestGombaTipusValasztas.test_gomba_tipus_valasztas_sikeres(scanner);
                    waitForEnter(scanner);
                    break;
                case 19:
                    TestGombatestFejleszteseSporaszintEleg.test_gombatest_fejlesztese_megfelelo_sporaszinttel();
                    waitForEnter(scanner);
                    break;
                case 20:
                    TestGombatestFejleszteseSporaszintNemEleg.test_gombatest_nem_fejlesztheto_nincs_sporaszint();
                    waitForEnter(scanner);
                    break;
                case 21:
                    TestGombatestElhalSporaSzetszorasUtan.TestGombatestElhalSporaSzetszorasUtan();
                    waitForEnter(scanner);
                    break;
                case 22:
                    TestTobbHatasARovaron.test_tobb_hatas_a_rovaron();
                    waitForEnter(scanner);
                    break;
                case 0 : 
                    System.out.println("Kilépés...");
                    break;
                default:
                    System.out.println("Érvénytelen választás.");
                    break;
            }

        }while(choice != 0);
        
        scanner.close();
    }

    /**
     * Waits for the user to press Enter before continuing.
     * 
     * @param scanner The Scanner object used to read user input.
     */
    private void waitForEnter(Scanner scanner) {
        System.out.println("\nNyomjon entert a folytatáshoz...");
        scanner.nextLine();
    }

    /**
     * Prints the available test options to the console.
     */
    private void printTestOptions() {
        System.out.println("Válasszon egy tesztesetet az alábbi listából:");
        System.out.println("1 - test_gombafonal_elvagasa_nincs_spora_hatas");
        System.out.println("2 - test_gombafonal_elvagasa_rovar_spora_hatas");
        System.out.println("3 - test_rovar_atlep_masik_tektonra");
        System.out.println("4 - test_gombafonal_novesztese_multitype_tektonon");
        System.out.println("5 - test_gombafonal_novesztese_single_type_tektonon");
        System.out.println("6 - test_rovar_elfogyaszt_sporat_es_megkapja_hatasat");
        System.out.println("7 - test_gombafonal_novesztese_nem_szomszedos_tektonok_kozott");
        System.out.println("8 - test_gombafonal_felszivodasa_felszivo_tektonon");
        System.out.println("9 - test_gombafonal_elszakadasa_tekton_toresnel");
        System.out.println("10 - test_tekton_tores_szomszedok_frissitese");
        System.out.println("11 - test_uj_gombatest_novesztese_sikeres");
        System.out.println("12 - test_uj_gombatest_novesztese_sikertelen_nincs_spora");
        //previous TEST 13 Removed - 
        System.out.println("13 - test_uj_gombatest_nem_no_ha_mar_van");
        System.out.println("14 - test_spora_kilovese_szomszedos_tektonra");
        System.out.println("15 - test_spora_kilovese_szomszeddal_szomszedos_tektonra");
        System.out.println("16 - test_spora_gyorsitja_fonal_novekedest");
        System.out.println("17 - test_tekton_nem_torhet_gombatest_miatt");
        System.out.println("18 - test_gomba_tipus_valasztas_sikeres");
        System.out.println("19 - test_gombatest_fejlesztese_megfelelo_sporaszinttel");
        System.out.println("20 - test_gombatest_nem_fejlesztheto_nincs_sporaszint");
        System.out.println("21 - test_gombatest_elhal_spora_szetszoras_utan");
        System.out.println("22 - test_tobb_hatas_a_rovaron");
        System.out.println("0 - Kilépés");
    }
}
