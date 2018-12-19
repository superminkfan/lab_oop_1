package com.company;

public class  Matrix<matrix> {
/*
сама матрица
заполнение случайно конструктор с аргументом
заполнение матрицы ручками
заполнение списка смежности
добавьь вершину
удалить вершину
провериить связность
 */
    //-----------------------------------------------
    //свойства
    private int [][] matrix;
    private int tops;

    //-----------------------------------------------
    //конструкторы


    public Matrix(int[][] matrix)
    {
        this.matrix = matrix;
        tops = matrix.length;

    }


//------------------------------------------------
    //методы

    public void showMatrix()
    {
        //System.out.println(tops);
        System.out.println("Матрица cмежности: ");
        for (int i=0 ;  i<matrix.length ; i++ )
        {
            System.out.println("");
            for (int j = 0; j < matrix.length; j++) {

                System.out.printf("%d", matrix[i][j]);
                System.out.printf("  ");

            }
        }
    }


    public boolean check_connectivity(){
        int kol=0;
        for(int From=1;From<matrix.length;From++)
            for (int To=2;To<matrix.length;To++)
            {
                if(From!=To){
                    boolean visited[]=new boolean[matrix.length];//массив отметок вершин
                    int v=From, u=0;
                    int top=0;//обнуляем список вершин - стек
                    int stack[]=new int[matrix.length+1];//Вначале все вершины не отмечены

                    top++;
                    stack[top]=v;//Помещаем начало маршрута в структуру данныx

                    visited[v-1]=true;//отмечаем начальную вершину
                    boolean if_push=false;
                    while(true)
                    {
                        if_push = false;

                        if (top>=0) u=stack[top];
                        else u=0;

                        if (u==To)break;

                        for(int j=0;j<matrix.length;j++)
                        {
                            if ((matrix[u-1][j]>=1)&&(visited[j]==false))
                            {
                                top+=1;
                                stack[top]=j+1;
                                visited[j]=true;
                                if_push=true;
                            }
                        }

                        if(!if_push)
                            if(top >= 0)
                            {
                                top-=1;
                            }

                        if (top==0)break;
                    }
                    if(top>0) kol++;
                }
            }
        if (kol>=matrix.length-1) return true;
        else return false;
    }

    public void  add_top(){
        int DelV=0;
        for (int i=0;i<matrix.length;i++)
        {
            if (this.matrix[i][i]==-1) DelV=i+1;
            break;
        }

        if(DelV>0) {
            for (int j = 0; j < matrix.length; j++){
                this.matrix[DelV-1][j] = 0;
                this.matrix[j][DelV-1] = 0;
            }
        }
        else{
            int[][] copy_matrix=new int[this.tops][this.tops];

            for (int i=0;i<matrix.length;i++)
                for (int j = 0; j < matrix.length; j++)
                    copy_matrix[i][j] = matrix[i][j];

            this.tops++;

            this.matrix = new int[this.tops][this.tops];

            for (int i=0;i<matrix.length-1;i++)
                for (int j = 0; j < matrix.length-1; j++) this.matrix[i][j] = copy_matrix[i][j];

        }
    }

    public void add_rebro(int v1, int v2){
        this.matrix[v1][v2]=1;
        this.matrix[v2][v1]=1;
    }

    public void del_top(int V) {
        for (int j = 0; j < matrix.length; j++) {
            this.matrix[V][j] = -1;
            this.matrix[j][V ] = -1;
        }
    }

    public void del_rebro(int v1,int v2){
        this.matrix[v1][v2]=0;
        this.matrix[v2][v1]=0;
    }
    //------------------------------------------------
    //сеттеры и геттеры


    public int[][] getMatrix() {
        return matrix;
    }


}
