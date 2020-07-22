#----------------------------------------------------------------------------------
# Author -------------- Cecilia Y. Sui
# Course -------------- Computer Graphics
# Instructor ---------- Dr. Crawley
# Submission Date ----- September 11, 2019
# Language Used ------- Python 3
# Program Description - This program implements the 3 basic transformations: 
# --------------------- translation, scaling, rotation. 
#----------------------------------------------------------------------------------

#----------------------------------------------------------------------------------
# imports 
#----------------------------------------------------------------------------------
import math

#----------------------------------------------------------------------------------
# Function Definitions
#----------------------------------------------------------------------------------

# Matrix Multiplication -----------------------------------------------------------
# A is 3 by 3
# B is 3 by 1
def MatrixMult(A, B):
    if len(A) != 3: 
        print("Wrong dimension for transformation matrix")
    elif len(B) != 3: 
        print("Wrong dimension for coordinate matrix")
    else: 
        result = [0 for i in range(3)]
        for i in range(3): 
            for j in range(3):
                result[i] += A[i][j]*B[j]
        return result


# Translation ---------------------------------------------------------------------
def translate(B, fact_x, fact_y):
    # B is the original coordinates 
    B.append(1)
    
    # populate identity matrix
    I = [[0 for i in range(3)] for j in range(3)]
    I[0][0], I[1][1], I[2][2] = 1,1,1
    I[0][2] = fact_x; 
    I[1][2] = fact_y; 
    
    # matrix multiplicaiton
    result = MatrixMult(I, B)
    return round(result[0],2), round(result[1],2)


# Scaling ------------------------------------------------------------------------
def scale(B, scale_x, scale_y):
    # B is the original coordinates
    B.append(1)
    
    # populate scaling matrix & ask user for scaling factors (a,b)
    I = [[0 for i in range(3)] for j in range(3)]
    I[2][2] = 1
    I[0][0] = scale_x
    I[1][1] = scale_y
    
    # matrix multiplication
    result = MatrixMult(I, B)
    return round(result[0],2), round(result[1],2)


# Rotation ------------------------------------------------------------------------
def rotate(B, angle):
    # B is the original coordinates
    B.append(1)
    r = angle * 3.1415926535 / 180
    
    # populate scaling matrix
    I = [[0 for i in range(3)] for j in range(3)]
    cosr = math.cos(r)
    sinr = math.sin(r)
    I[0][0], I[1][1] = cosr, cosr
    I[0][1], I[1][0] = -sinr, sinr

    # matrix multiplication
    result = MatrixMult(I, B)
    return round(result[0],2), round(result[1],2)

#----------------------------------------------------------------------------------
# Main Function 
#----------------------------------------------------------------------------------
def main(): 
    x = float(input("x coordinate: "))
    y = float(input("y coordinate: "))
    Coor = [x,y]
    user = input("Choose transformation: \nEnter T for translation, S for scaling, R for rotation: ").strip().upper()[0]
    if user == "T":
        trans_x = float(input("Translation factor for x: "))
        trans_y = float(input("Translation factor for y: "))
        print("New point at:", translate(Coor, trans_x, trans_y))
    elif user == "S":
        scale_x = float(input("Scaling factor for x: "))
        scale_y = float(input("Scaling factor for y: "))
        print("New point at:", scale(Coor, scale_x, scale_y))
    elif user == "R":
        R = float(input("Rotation angle in degrees (r): "))
        print("New point at:", rotate(Coor, R))
    else: 
        print("Invalid Input.")

if __name__ == "__main__":
    main()