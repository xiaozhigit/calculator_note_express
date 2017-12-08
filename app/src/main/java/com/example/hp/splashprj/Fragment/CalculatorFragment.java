package com.example.hp.splashprj.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.splashprj.R;

/**
 * Created by hp on 2017/10/26.
 */

public class CalculatorFragment extends Fragment implements View.OnClickListener{
     private View view;
    //定义数字按钮
    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    //定义运算符
    private Button btn_add, btn_sub, btn_mul, btn_divide, btn_equal, btn_dot;
    //定义退格，清空按钮；
    private Button btn_ac, btn_delete;
    //定义结果显示
    private EditText resultText;
    //--------------------定义计算器逻辑实现标记符-----------------------
    //*定义已经输入的字符
    private String existedText = "";
    //*是否计算
    private boolean isCounted = false;
    //*以负号开始，运算符不是减号
    private boolean startWithOpreator = false;
    //*以负号开始，运算符为减号
    private boolean startWithSub = false;
    //*不以负号开始，切包含运算符
    private boolean noStartWithOpreator = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
         view=inflater.inflate(R.layout.calaculator_fragment,null);
        initView();
        initEvent();
        return view;
    }
    //初始化控件
    private void initView() {
        //数字
        btn_0 = (Button) view.findViewById(R.id.btn_0);
        btn_1 = (Button) view.findViewById(R.id.btn_1);
        btn_2 = (Button)view. findViewById(R.id.btn_2);
        btn_3 = (Button)view. findViewById(R.id.btn_3);
        btn_4 = (Button)view. findViewById(R.id.btn_4);
        btn_5 = (Button) view.findViewById(R.id.btn_5);
        btn_6 = (Button) view.findViewById(R.id.btn_6);
        btn_7 = (Button)view. findViewById(R.id.btn_7);
        btn_8 = (Button)view. findViewById(R.id.btn_8);
        btn_9 = (Button)view. findViewById(R.id.btn_9);
        //运算符
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_sub = (Button) view.findViewById(R.id.btn_sub);
        btn_mul = (Button) view.findViewById(R.id.btn_mul);
        btn_divide = (Button)view. findViewById(R.id.btn_divide);
        btn_equal = (Button) view.findViewById(R.id.btn_equal);
        btn_dot = (Button)view. findViewById(R.id.btn_dot);
        //操作符
        btn_ac = (Button)view. findViewById(R.id.btn_ac);
        btn_delete = (Button) view.findViewById(R.id.btn_delete);
        //结果显示文本及以输入字符
        resultText = (EditText) view.findViewById(R.id.result_text);
        existedText = resultText.getText().toString().trim();
        resultText.setSelection(existedText.length());
    }

    //所有按钮设置监听器
    private void initEvent() {
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_ac.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    //按钮点击事件处理
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //数字
            case R.id.btn_0:
                existedText = isOverRange(existedText, "0");
                break;
            case R.id.btn_1:
                existedText = isOverRange(existedText, "1");
                break;
            case R.id.btn_2:
                existedText = isOverRange(existedText, "2");
                break;
            case R.id.btn_3:
                existedText = isOverRange(existedText, "3");
                break;
            case R.id.btn_4:
                existedText = isOverRange(existedText, "4");
                break;
            case R.id.btn_5:
                existedText = isOverRange(existedText, "5");
                break;
            case R.id.btn_6:
                existedText = isOverRange(existedText, "6");
                break;
            case R.id.btn_7:
                existedText = isOverRange(existedText, "7");
                break;
            case R.id.btn_8:
                existedText = isOverRange(existedText, "8");
                break;
            case R.id.btn_9:
                existedText = isOverRange(existedText, "9");
                break;
            //运算符
            case R.id.btn_add:
                //判断表达式是否可以计算 是 先计算再添加运算符 否 添加运算符
                //判断计算后的字符是否为error 是 置零 否 添加运算符
                if (judgeExpression()) {
                    existedText = getResult();
                    if (existedText.equals("error")) {
                    } else {
                        existedText += "+";
                    }
                } else {
                    if (isCounted) {
                        isCounted = false;
                    }
                    Log.d("existedText", existedText);
                    if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("-", "+");
                    } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("×", "+");
                    } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("÷", "+");
                    } else if (!(existedText.substring((existedText.length() - 1)).equals("+"))) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText += "+";
                    }
                }
                break;
            case R.id.btn_sub:
                //判断表达式是否可以计算 是 先计算再添加运算符 否 添加运算符
                //判断计算后的字符是否为error 是 置零 否 添加运算符
                if (judgeExpression()) {
                    existedText = getResult();
                    if (existedText.equals("error")) {
                    } else {
                        existedText += "-";
                    }
                } else {
                    if (isCounted) {
                        isCounted = false;
                    }
                    Log.d("existedText", existedText);
                    if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("×", "-");
                    } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("÷", "-");
                    } else if ((existedText.substring((existedText.length() - 1)).equals("+"))) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("+", "-");
                    } else if (!(existedText.substring(existedText.length() - 1)).equals("-")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText += "-";
                    }
                }
                break;
            case R.id.btn_mul:
                //判断表达式是否可以计算 是 先计算再添加运算符 否 添加运算符
                //判断计算后的字符是否为error 是 置零 否 添加运算符
                if (judgeExpression()) {
                    existedText = getResult();
                    if (existedText.equals("error")) {
                    } else {
                        existedText += "×";
                    }
                } else {
                    if (isCounted) {
                        isCounted = false;
                    }
                    Log.d("existedText", existedText);
                    if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("-", "×");
                    } else if ((existedText.substring(existedText.length() - 1)).equals("+")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("+", "×");
                    } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("÷", "×");
                    } else if (!(existedText.substring((existedText.length() - 1)).equals("×"))) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText += "×";
                    }
                }
                break;
            case R.id.btn_divide:
                //判断表达式是否可以计算 是 先计算再添加运算符 否 添加运算符
                //判断计算后的字符是否为error 是 置零 否 添加运算符
                if (judgeExpression()) {
                    existedText = getResult();
                    if (existedText.equals("error")) {
                    } else {
                        existedText += "÷";
                    }
                } else {
                    if (isCounted) {
                        isCounted = false;
                    }
                    Log.d("existedText", existedText);
                    if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("-", "÷");
                    } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("×", "÷");
                    } else if ((existedText.substring(existedText.length() - 1)).equals("+")) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText = existedText.replace("+", "÷");
                    } else if (!(existedText.substring((existedText.length() - 1)).equals("÷"))) {
                        Log.d("existedText_substring", existedText.substring(existedText.length() - 1));
                        existedText += "÷";
                    }
                }
                break;
            case R.id.btn_equal:
                existedText = getResult();
                isCounted = true;
                break;
            //其他
            case R.id.btn_dot:
                //判断是否运算过 是 字符串置为零
                //否 判断是否有运算符， 有 判断运算符之后的数字 无 判段整个数字
                //判断数字是否过长 是 不能添加小数点，否 可以添加
                // 判断是否有小数点 是 字符串置为零
                if (!isCounted) {
                    if (existedText.contains("+") || existedText.contains("-") ||
                            existedText.contains("×") || existedText.contains("÷")) {
                        String param1 = null, param2 = null;
                        if (existedText.contains("+")) {
                            param1 = existedText.substring(0, existedText.indexOf("+"));
                            param2 = existedText.substring(existedText.indexOf("+") + 1);
                        } else if (existedText.contains("-")) {
                            param1 = existedText.substring(0, existedText.indexOf("-"));
                            param2 = existedText.substring(existedText.indexOf("-") + 1);
                        } else if (existedText.contains("×")) {
                            param1 = existedText.substring(0, existedText.indexOf("×"));
                            param2 = existedText.substring(existedText.indexOf("×") + 1);
                        } else if (existedText.contains("÷")) {
                            param1 = existedText.substring(0, existedText.indexOf("÷"));
                            param2 = existedText.substring(existedText.indexOf("÷") + 1);
                        }
                        Log.d("Anonymous param1", param1);
                        Log.d("Anonymous param2", param2);
                        boolean isContainedDot = param2.contains(".");
                        if (param2.length() > 9) {
                        } else if (!isContainedDot) {
                            if (param2.equals("")) {
                                existedText += "0.";
                            } else {
                                existedText += ".";
                            }
                        } else {
                            return;
                        }
                    } else {
                        boolean isContainedDot = existedText.contains(".");
                        if (existedText.length() > 9) {
                        } else if (!isContainedDot) {
                            existedText += ".";
                        } else {
                            return;
                        }
                    }
                    isCounted = false;
                } else {
                    existedText = "0.";
                    isCounted = false;
                }
                break;
            case R.id.btn_delete:
                //字符串长度大于零时才截取字符串 如果长度为1，则直接把字符串设置为零
                if (existedText.equals("error")) {
                    existedText = "0";
                } else if (existedText.length() > 0) {
                    if (existedText.length() == 1) {
                        existedText = "0";
                    } else {
                        existedText = existedText.substring(0, existedText.length() - 1);

                    }
                }
                break;
            case R.id.btn_ac:
                existedText = "0";
                break;

        }
        resultText.setText(existedText);
        resultText.setSelection(existedText.length());
    }

    //进行运算得到结果
    private String getResult() {
        //定义结果及运算数
        String tempResult = null, param1 = null, param2 = null;
        //定义转换后的两个double类型参数
        double arg1 = 0, arg2 = 0, result = 0;
        getCondition();
        //如果有运算符，则进行运算没有则把以存在的数据传出去
        if (startWithSub || startWithOpreator || noStartWithOpreator) {
            if (existedText.contains("+")) {
                //获取两个计算数
                param1 = existedText.substring(0, existedText.indexOf("+"));
                param2 = existedText.substring(existedText.indexOf("+") + 1);
                //如果第二参数为空，则显示当前字符
                if (param2.equals("")) {
                    tempResult = existedText;
                } else {
                    //将字符串进行转换在运算
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 + arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }
            } else if (existedText.contains("-")) {
                //获取两个计算数
                param1 = existedText.substring(0, existedText.lastIndexOf("-"));
                param2 = existedText.substring(existedText.lastIndexOf("-") + 1);
                //如果第二参数为空或为零，则显示当前字符
                if (param2.equals("")) {
                    tempResult = existedText;
                } else {
                    //将字符串进行转换在运算
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 - arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }
            } else if (existedText.contains("×")) {
                //获取两个计算数
                param1 = existedText.substring(0, existedText.indexOf("×"));
                param2 = existedText.substring(existedText.indexOf("×") + 1);
                //如果第二参数为空，则显示当前字符，若除数为零则提示。
                if (param2.equals("")) {
                    tempResult = existedText;
                } else if (param2.equals("0")) {
                    tempResult = "除数不能为零";
                } else {
                    //将字符串进行转换在运算
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 * arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }
            } else if (existedText.contains("÷")) {
                //获取两个计算数
                param1 = existedText.substring(0, existedText.indexOf("÷"));
                param2 = existedText.substring(existedText.indexOf("÷") + 1);
                //如果第二参数为空，则显示当前字符
                if (param2.equals("")) {
                    tempResult = existedText;
                } else {
                    //将字符串进行转换在运算
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 / arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }
            }

        } else {
            tempResult = existedText;
        }
        return tempResult;
    }

    //判断已输入字符串是否越界；
    private String isOverRange(String existedText, String in) {
        //判断是否计算过
        if (!isCounted) {
            //判断是否只有一个零，是 清空文本
            if (existedText.equals("0")) {
                existedText = "";
            }
            //判断是否有运算符 是判断第二个数字，否判断整个字符串
            if (existedText.contains("+") || existedText.contains("-") ||
                    existedText.contains("×") || existedText.contains("÷")) {
                //包括运算符时 两个数字，判断第二个数字
                String param2 = null;
                if (existedText.contains("+")) {
                    param2 = existedText.substring(existedText.indexOf("+") + 1);
                } else if (existedText.contains("-")) {
                    param2 = existedText.substring(existedText.indexOf("-") + 1);
                } else if (existedText.contains("×")) {
                    param2 = existedText.substring(existedText.indexOf("×") + 1);
                } else if (existedText.contains("÷")) {
                    param2 = existedText.substring(existedText.indexOf("÷") + 1);
                }
                Log.d("param2", param2);
                if (existedText.substring(existedText.length() - 1).equals("+")
                        || existedText.substring(existedText.length() - 1).equals("-")
                        || existedText.substring(existedText.length() - 1).equals("×")
                        || existedText.substring(existedText.length() - 1).equals("÷")
                        ) {
                    existedText += in;
                } else {
                    if (param2.contains(".")) {
                        if (param2.length() >= 10) {
                        } else {
                            existedText += in;
                        }
                    } else {
                        if (param2.length() >= 9) {
                        } else {
                            existedText += in;
                        }
                    }
                }

            } else {
                //不包括运算符时 一个数字
                if (existedText.contains(".")) {
                    if (existedText.length() >= 10) {
                    } else {
                        existedText += in;
                    }
                } else {
                    if (existedText.length() >= 9) {
                    } else {
                        existedText += in;
                    }
                }
            }
            isCounted = false;
        } else {
            existedText = in;
            isCounted = false;
        }
        return existedText;
    }

    //使用Java正则表达式去掉多余的小数点和零
    public static String subZeroAndDot(String s_in) {
        if (s_in.indexOf(".") > 0) {
            s_in = s_in.replaceAll("0+?$", "");//去掉多余的零
            s_in = s_in.replaceAll("[.]$", "");//如果最后一位是小数点，则去掉
        }
        return s_in;
    }

    //判断字符串是否符合算式要求
    private boolean judgeExpression() {
        getCondition();//得到初始化条件；
        String tempParam2 = null;
        if (startWithOpreator || noStartWithOpreator || startWithSub) {
            if (existedText.contains("+")) {
                //先获取第二个参数
                tempParam2 = existedText.substring(existedText.indexOf("+") + 1);
                //如果第二个参数为空，表达式不成立
                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }
            } else if (existedText.contains("-")) {
                //先获取第二个参数
                //以最后一个负号来分割两个参数
                tempParam2 = existedText.substring(existedText.lastIndexOf("-") + 1);
                //如果第二个参数为空，表达式不成立
                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }
            } else if (existedText.contains("×")) {
                //获取第二个参数
                tempParam2 = existedText.substring(existedText.indexOf("×") + 1);
                //如果第二个参数为空，表达式不成立
                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }
            } else if (existedText.contains("÷")) {
                //先获取第二个参数
                tempParam2 = existedText.substring(existedText.indexOf("÷") + 1);
                //如果第二个参数为空或为零，表达式不成立
                if (tempParam2.equals("") || tempParam2.equals("0")) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    //取得判断条件
    private void getCondition() {
        //以负号开始，且运算符不是减号
        startWithOpreator = existedText.startsWith("-") && (existedText.contains("+")
                || existedText.contains("×") || existedText.contains("÷"));
        //以负号开始，且运算符是减号
        startWithSub = existedText.startsWith("-") && (existedText.lastIndexOf("-") != 0);
        //不以负号开始，且包含运算符
        noStartWithOpreator = !existedText.startsWith("-") && (existedText.contains("+") ||
                existedText.contains("-") || existedText.contains("×") || existedText.contains("÷"));
    }

}
