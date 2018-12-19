package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int k;
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        Matrix mat ;
        //нужно придумать так что этот мат будет главной ссылкой
        //и только его мы будем посылать в алгоритм дейкстры и в действия с матрицами
        //изначально есть какая-то мтарица смежности
        //здесь вывести матриу смежности сгенерированную вот где то сдесь?
        int [][] wat_mat = {
                {0,0,0,1},
                {0,0,1,0},
                {0,1,0,1},
                {1,0,1,0}
        };

        mat = new Matrix(wat_mat);
        mat.showMatrix();


        do {
            System.out.println("");
            System.out.println("----МЕНЮ----");
            System.out.println("1---Случайно заполнить матрицу смежности");
            System.out.println("2---Заполниить матрицу смежности самому");
            System.out.println("3---Заполнить спислк смежности");
            System.out.println("4---Проверить связность графа");
            System.out.println("5---Добавить вершину");
            System.out.println("6---Удалить вершину");
            System.out.println("7---Добавить ребро");
            System.out.println("8---Удалить ребро");
            System.out.println("9---Алгоритм Дейкстры");
            System.out.println("0---Выход");
            k = scan.nextInt();

            switch (k) {
                case 1: {//случайно заполнить матрицу смежности
                         //по сути нужно создать матрицу сдесь
                         //и передать её как аргумент в конструктора
                         //mat = new Matrix();
                    int [][] ran_mat = new int [4][4];

                    for (int i = 0; i<4 ; i++)
                    {
                        for (int j = 0; j <4; j++) {

                            int s = ran.nextInt(2);
                            ran_mat[i][j]= s;
                            ran_mat[j][i]=s;
                            if (i == j) {
                                ran_mat[i][j]=0;
                            }

                        }
                    }
                    //здесь создавать объеккт и копировать его или всё время перезаписывать одни объект
                        mat = new Matrix(ran_mat);
                        mat.showMatrix();
                    break;

                }
                case 2: {// заполнить матрицу смежности самому
                         //тут вводить количество вершин
                         //поэтому нужно создать конструктор с аргументом типа кол во вершин
                    System.out.println("Введите количество вершин ");
                    int tops = scan.nextInt();
                    int [][] new_mat = new int [tops][tops];
                    for (int i = 0 ; i<tops-1 ; i++)
                    {
                        for (int j = 1; j < tops; j++)
                        {
                            if ( i == j )
                            {
                                System.out.println("Элемент [" + (i) + "][" + (j) + "] и элемент [" + (j) + "][" + (i) + "] равняется 0");
                                continue;
                            }
                            System.out.println("Введите элемент [" + (i) + "][" + (j) + "] и элемент [" + (j) + "][" + (i) + "]" );
                            int elem = scan.nextInt();
                            new_mat[i][j] = elem;
                            new_mat[j][i] = elem;
                        }
                    }
                    mat = new Matrix(new_mat);
                    mat.showMatrix();
                    break;


                }
                case 3: {// заполнить списки смежности самому
                         //причем надо объяснить( а перед этим придумать) правила заполнения таких списков
                         //то что ввёл надо перекинуть в матрикс и там это обработать

                    System.out.println("Введите количество вершин ");
                    int tops = scan.nextInt();
                    int [][] new_mat = new int [tops][tops];
                    System.out.println("после двоеточия записать смежные вершины каждый раз нажимая enter ");
                    System.out.println("чтобы переключится на другую вершину необхадимо просто нажать на enter ");
                    System.out.println("Вершины   :   Смежные вершины");
                   try {
                       for (int i = 0; i < tops; i++) {
                           System.out.printf("%d", i);
                           System.out.printf("  :  ");

                           Scanner sc = new Scanner(System.in);
                           //String s = sc.nextLine();
                           String s = "s";
                           while (!s.equals("")) {
                               s = sc.nextLine();
                               if (!s.equals("")) {
                                   int j = Integer.parseInt(s);
                                   if (i == j) {
                                       System.out.println("тут некрасивая петля");
                                       new_mat[i][j] = 0;
                                   } else {
                                       new_mat[i][j] = 1;
                                       new_mat[j][i] = 1;
                                   }
                               }

                           }
                       }

                       mat = new Matrix(new_mat);
                       mat.showMatrix();
                   } catch (Exception e)
                   {
                       System.out.println("Ошибка ввода!");
                   }
                    break;
                }
                case 4: {// провериьь связность графа
                    if (mat.check_connectivity()==true)
                        System.out.println("Граф связный");
                    else
                        System.out.println("Граф не связный");
                    break;

                }
                case 5: {//добавление вершины в граф
                    //System.out.println("");
                    mat.add_top();
                    mat.showMatrix();
                    break;

                }
                case 6:{//удаление вершины

                    System.out.println("Введите номер удалемой вершины ");
                    int v = scan.nextInt();
                  try{
                      mat.del_top(v);
                  } catch (Exception e)
                  {
                      System.out.println("Такой вершины нет!");
                  }
                    mat.showMatrix();
                    break;

                }
                case 7: {//добавление ребра
                    System.out.println("Введите номер первой вершины");
                    int v1 = scan.nextInt();
                    System.out.println("Введите номер второй вершины");
                    int v2 = scan.nextInt();
                   try {
                       mat.add_rebro(v1,v2);
                   } catch (Exception e)
                   {
                       System.out.println("Неправильный ввод данных!");
                   }
                    mat.showMatrix();
                    break;
                }

                case 8:{//удаление ребра
                    System.out.println("Введите номер первой вершины");
                    int v1 = scan.nextInt();
                    System.out.println("Введите номер второй вершины");
                    int v2 = scan.nextInt();
                    try {
                        mat.del_rebro(v1, v2);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Неправельнный ввод данных!");
                    }
                    mat.showMatrix();
                    break;
                }

                case 9: {//алгоритм дейкстры
                    Deykstra dey = new Deykstra();
                    System.out.println("Введите стартовую вершину ");
                    dey.setS(scan.nextInt());
                    System.out.println("Введите конечную вершину ");
                    dey.setF(scan.nextInt());
                    try {
                        dey.AlgDeykstr(mat.getMatrix());
                    }
                    catch (Exception e)
                    {
                        System.out.println("Ошибка ввода");
                    }

                    break;
                }
                case 0:{//выход
                    break;
                }


            }
        } while (k>0 && k<10);

    }
}
